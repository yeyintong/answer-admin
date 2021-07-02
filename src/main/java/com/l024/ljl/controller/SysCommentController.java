package com.l024.ljl.controller;

import com.l024.ljl.entity.SysCommentEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import com.l024.ljl.service.SysCommentService;
import com.l024.ljl.service.SysUserSaveService;
import com.l024.ljl.service.SysUserService;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="用户评论")
@RestController
@RequestMapping("/comment")
public class SysCommentController {
    @Autowired
    private SysCommentService sysCommentService;
    @ApiOperation(httpMethod = "POST", value = "添加用户评论", response = R.class,
            notes = "添加用户评论 ")
    @PostMapping("/add")
    public R add(@RequestBody Map<String,Object> map) {
        if(map!=null){
            Long userId = Long.valueOf(map.get("userId")+"");
            Long subjectId =Long.valueOf(map.get("subjectId")+"");
            String comment = map.get("comment")+"";
            boolean sava = sysCommentService.sava(userId, subjectId,comment);
            if(sava){
                return R.ok("添加成功",null);
            }

        }
        return R.error(500,"评论失败");
    }

    @ApiOperation(httpMethod = "GET", value = "获取用户评论", response = R.class,
            notes = "获取用户评论 ")
    @GetMapping("/user/{userId}")
    public R getUserAll(@PathVariable("userId") Long userId) {
        List<SysCommentEntity> comments = sysCommentService.getUserComments(userId);
        return R.ok("获取成功",comments);
    }


    @ApiOperation(httpMethod = "GET", value = "获取试卷评论", response = R.class,
            notes = "获取试卷评论 ")
    @GetMapping("/subject/{subjectId}")
    public R getSubjectAll(@PathVariable("subjectId") Long subjectId) {
        List<SysCommentEntity> comments = sysCommentService.getSubjectComments(subjectId);
        return R.ok("获取成功",comments);
    }

}
