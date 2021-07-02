package com.l024.ljl.controller;

import com.l024.ljl.entity.SysUserEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import com.l024.ljl.service.SysUserSaveService;
import com.l024.ljl.util.StringUtil;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="用户收藏")
@RestController
@RequestMapping("/save")
public class SysUserSaveController {
    @Autowired
    private SysUserSaveService sysUserSaveService;

    @ApiOperation(httpMethod = "POST", value = "添加用户收藏", response = R.class,
            notes = "添加用户收藏 ")
    @PostMapping("/add")
    public R add(@RequestBody Map<String,Long> map) {
        if(map!=null){
            Long userId = map.get("userId");
            Long subjectId = map.get("subjectId");
            String sava = sysUserSaveService.sava(userId, subjectId);
            Map<String,String> m = new HashMap<>();
            m.put("object",sava);
            return R.ok(sava,m);
        }
        return R.error(500,"收藏失败");
    }

    @ApiOperation(httpMethod = "GET", value = "添加用户收藏", response = R.class,
            notes = "添加用户收藏 ")
    @GetMapping("/all/{userId}")
    public R getAll(@PathVariable("userId") Long userId) {
        List<SysUserSaveEntity> userSaves = sysUserSaveService.getUserSaves(userId);
        return R.ok("获取成功",userSaves);
    }

}
