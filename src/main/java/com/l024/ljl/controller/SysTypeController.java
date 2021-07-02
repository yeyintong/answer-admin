package com.l024.ljl.controller;


import com.l024.ljl.entity.SysTypeEntity;
import com.l024.ljl.service.SysTypeService;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value="类型列表")
@RestController
@RequestMapping("/type")
public class SysTypeController implements BaseController<SysTypeEntity>{
    @Autowired
    private SysTypeService sysTypeService;

    @ApiOperation(httpMethod = "POST",value = "添加类型",response = R.class,notes = "添加类型")
    @PostMapping("/save")
    @Override
    public R add(SysTypeEntity sysTypeEntity) {
        boolean add = sysTypeService.add(sysTypeEntity);
        if(!add)
            return R.error("添加失败");
        else
            return R.ok("添加成功");

    }

    @ApiOperation(httpMethod = "POST",value ="删除类型",response = R.class,notes = "删除类型")
    @PostMapping("/del")
    @Override
    public R del(Long id) {
        boolean del = sysTypeService.del(id);
        if(del)
            return R.ok("删除成功");
        return R.error("删除失败");
    }

    @Override
    public R update(SysTypeEntity sysTypeEntity) {
        return null;
    }


    @ApiOperation(httpMethod = "GET", value = "获取所有类型", response = R.class,
            notes = "获取所有类型")
    @GetMapping("/all")
    @Override
    public R getAll() {
        return R.ok("获取所有类型",sysTypeService.getAll());
    }

    @Override
    public R get(SysTypeEntity sysTypeEntity) {
        return null;
    }

    @Override
    public R page(Map<String, Object> map) {
        return null;
    }



}
