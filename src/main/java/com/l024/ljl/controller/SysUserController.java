package com.l024.ljl.controller;

import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.service.SysUserService;
import com.l024.ljl.service.TokenService;
import com.l024.ljl.util.LogUtil;
import com.l024.ljl.util.StringUtil;
import com.l024.ljl.util.TokenUtil;
import com.l024.ljl.util.UserLoginToken;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value="系统用户")
@RestController
@RequestMapping("/user")
public class SysUserController implements BaseController<SysUserEntity> {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    TokenService tokenService;

    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    /**
     * 图片保存路径，自动从yml文件中获取数据
     *   示例： E:/images/
     */
    @Value("${file-save-path}")
    private String fileSavePath;

    @ApiOperation(httpMethod = "POST", value = "用户添加", response = R.class,
            notes = "系统用户添加 ")
    @PostMapping("/add")
    @Override
    public R add(@RequestBody SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null&&!StringUtil.isEmpty(sysUserEntity.getPhone())){
            //先查询用户是否存在
            SysUserEntity user = sysUserService.getUserByPhone(sysUserEntity.getPhone());
            if(user!=null){
                return R.error(500,"该用户手机号已经注册，请更新手机号");
            }
            boolean add = sysUserService.add(sysUserEntity);
            if(add){
                return R.ok("注册成功",null);
            }
        }
        return R.error(500,"注册失败");
    }

    @ApiOperation(httpMethod = "GET", value = "删除用户", response = R.class,
            notes = "删除用户")
    @GetMapping("/del")
    @UserLoginToken
    @Override
    public R del(Long id) {
        if(id>0){
            boolean del = sysUserService.del(id);
            if(del){
                return R.ok("删除成功","");
            }
        }
        return R.error(500,"删除失败");
    }

    @ApiOperation(httpMethod = "POST", value = "更新用户信息", response = R.class,
            notes = "更新用户信息")
    @PostMapping("/update")
    @UserLoginToken
    @Override
    public R update(@RequestBody SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null&&sysUserEntity.getId()>0){
            boolean update = sysUserService.update(sysUserEntity);
            if(update){
                return R.ok("更新成功","");
            }
        }
        return R.error(500,"更新失败");
    }

    @ApiOperation(httpMethod = "GET", value = "获取所有用户信息", response = R.class,
            notes = "获取所有用户信息")
    @GetMapping("/all")
    @UserLoginToken
    @Override
    public R getAll() {
        return R.ok("获取所有用户成功",sysUserService.getAll());
    }

    @ApiOperation(httpMethod = "GET", value = "获取当前用户信息", response = R.class,
            notes = "获取当前用户信息")
    @GetMapping("/current/info")
    @UserLoginToken
    @Override
    public R get(SysUserEntity sysUserEntity) {
        String tokenPhone = TokenUtil.getTokenUserPhone();
        SysUserEntity sysUser = sysUserService.getUserByPhone(tokenPhone);
        LogUtil.i("当前用户信息：",sysUser);
        return R.ok("获取用户详情",sysUser);
    }

    @Override
    public R page(Map<String, Object> map) {
        return null;
    }

    @ApiOperation(httpMethod = "POST", value = "登录", response = R.class,
            notes = "系统用户登录")
    @PostMapping("/login")
    public R login(@RequestBody SysUserEntity user) {
        if(user!=null&&!StringUtil.isEmpty(user.getPhone())&&!StringUtil.isEmpty(user.getPassword())){
            //查询数据库
            SysUserEntity dbUser = sysUserService.getUserByPhone(user.getPhone());
            if(dbUser!=null){
                String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                if(md5Password.equals(dbUser.getPassword())){
                    //获取token
                    String token = tokenService.getToken(dbUser);
                    dbUser.setToken(token);
                    LogUtil.i("当前登录用户信息：",dbUser);
                    return R.ok("登录成功",dbUser);
                }
            }
            return R.error(500,"账户名或者密码错误");
        }
        return R.error(500,"账户名或者密码不可为null");
    }


    @ApiOperation(httpMethod = "POST", value = "退出登录", response = R.class,
            notes = "系统用户退出登录")
    @PostMapping("/logout")
    @UserLoginToken
    public R logout(){
        LogUtil.i("退出登录：","");
        return R.ok("退出登录","");
    }


    @ApiOperation(httpMethod = "POST", value = "根据id或者手机号获取用户信息", response = R.class,
            notes = "获取用户信息")
    @PostMapping("/info")
    @UserLoginToken
    public R getUserInfo(@RequestBody SysUserEntity sysUserEntity) {
        if(sysUserEntity!=null){
            if(!StringUtil.isEmpty(sysUserEntity.getPhone())||sysUserEntity.getId()>0){
                SysUserEntity sysUser = sysUserService.getUserWhere(sysUserEntity);
                if(sysUser!=null){
                    return R.ok("获取用户详情成功",sysUser);
                }
            }
        }
        return R.error(500,"获取用户详情失败");
    }

    /**
     * 上传头像
     */
    @ApiOperation(httpMethod = "POST", value = "上传图片", response = R.class,
            notes = "上传图片")
    @PostMapping("/upload")
    public R uploadImage(MultipartFile file, HttpServletRequest request){
        LogUtil.i("文件大小:",file.getSize());
        LogUtil.i("文件大小:",file.getSize()/1024/1024+"M");
        if(file!=null&&file.getSize()/1024/1024>5){
            return R.error(500,"文件太大，请上传小于5M的图片");
        }
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LogUtil.i("文件保存地址",dir.getAbsolutePath());
        //3.给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName;
            LogUtil.i("图片上传，访问URL：" , url);
            //更新用户的头像信息
            String tokenPhone = TokenUtil.getTokenUserPhone();
            SysUserEntity sysUser = sysUserService.getUserByPhone(tokenPhone);
            if(sysUser!=null&&sysUser.getId()>0){
                sysUser.setAvator(url);
                //更新用户数据
                sysUserService.update(sysUser);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("object",url);
            return R.ok("图片上传成功",map);
        } catch (IOException e) {
            return R.error(500,"上传失败,IO异常");
        }
    }

}
