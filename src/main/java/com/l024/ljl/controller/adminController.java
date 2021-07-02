package com.l024.ljl.controller;
import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.service.SysUserService;
import com.l024.ljl.service.TokenService;
import com.l024.ljl.util.LogUtil;
import com.l024.ljl.util.StringUtil;
import com.l024.ljl.vo.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@ApiOperation(value = "管理员登录")
@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation(httpMethod = "POST",value = "管理员登录",response = R.class,notes = "管理员登录")
    @PostMapping("/login")
    public R login(SysUserEntity user){
        System.out.println(user);
        if(user==null)
            return R.error("输入不能为空");
        if(!StringUtil.isEmpty(user.getPhone())&&!StringUtil.isEmpty(user.getPassword())){
            //查询数据库
            SysUserEntity dbUser = sysUserService.getUserByPhone(user.getPhone());
            if(dbUser!=null){
                if(dbUser.getType()!=1)
                    return R.error("不是管理员，无权限登录");
                String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                if(md5Password.equals(dbUser.getPassword())){
                    //获取token
                    String token = tokenService.getToken(dbUser);
                    dbUser.setToken(token);
                    dbUser.setPassword("");//将密码置为空，不传入前端
                    LogUtil.i("当前登录用户信息：",dbUser);
                    return R.ok("登录成功",dbUser);
                }
            }
            return R.error(500,"账户名或者密码错误");
        }
        return R.error(500,"账户名或者密码不可为null");
    }

    @ApiOperation(httpMethod = "POST",value = "管理员注册",response = R.class,notes = "管理员注册")
    @PostMapping("/register")
    public R register(SysUserEntity sysUserEntity){
        if(sysUserEntity==null)
            return R.error("输入信息错误");
        if(!StringUtil.isEmpty(sysUserEntity.getPhone())&&!StringUtil.isEmpty(sysUserEntity.getPassword())){
            SysUserEntity userByPhone = sysUserService.getUserByPhone(sysUserEntity.getPhone());
            if(userByPhone!=null)
                return R.error("该用户已存在");
            sysUserEntity.setType(1);
            System.out.println(sysUserEntity);
            boolean add = sysUserService.add(sysUserEntity);
            if(add)
                return R.ok("注册成功");
            else
                return R.error("注册失败");
        }
        else
            return R.error("密码或手机不能为空");
    }
}
