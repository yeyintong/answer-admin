package com.l024.ljl.controller;


import com.l024.ljl.entity.SysTypeEntity;
import com.l024.ljl.service.SysTypeService;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R add(@RequestBody SysTypeEntity sysTypeEntity) {
        boolean add = sysTypeService.add(sysTypeEntity);
        if(!add)
            return R.error("添加失败");
        else
            return R.ok(200,"添加成功", sysTypeEntity);

    }

    @ApiOperation(httpMethod = "POST",value ="删除类型",response = R.class,notes = "删除类型")
    @PostMapping("/del")
    @Override
    public R del(Long id) {
        boolean del = sysTypeService.del(id);
        if(del)
            return R.ok(200,"删除成功");
        return R.error("删除失败");
    }

    @ApiOperation(httpMethod = "POST",value = "修改类型",response = R.class,notes = "修改类型")
    @PostMapping("/update")
    @Override
    public R update(@RequestBody SysTypeEntity sysTypeEntity) {
        boolean update = sysTypeService.update(sysTypeEntity);
        if (update)
            return R.ok(200,"更新成功");
        else
            return R.error("修改失败");
    }


    @ApiOperation(httpMethod = "GET", value = "获取所有类型", response = R.class,
            notes = "获取所有类型")
    @GetMapping("/all")
    @Override
    public R getAll() {
        return R.ok("获取所有类型",sysTypeService.getAll());
    }

    @GetMapping("/count")
    public R count(){
        long count = sysTypeService.count();
        return R.ok(200,"获取成功",count);

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
