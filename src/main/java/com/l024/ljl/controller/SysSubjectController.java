package com.l024.ljl.controller;

import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.service.SysSubjectService;
import com.l024.ljl.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value="题目")
@RestController
@RequestMapping("/subject")
public class SysSubjectController implements BaseController<SysSubjectEntity>{

    @Autowired
    private SysSubjectService sysSubjectService;

    @ApiOperation(httpMethod = "POST",value = "添加试题",response = R.class,notes = "添加试题")
    @PostMapping("/save")
    @Override
    public R add(@RequestBody SysSubjectEntity sysSubjectEntity) {
        boolean flag = sysSubjectService.add(sysSubjectEntity);

        if (!flag) {
            return R.error("添加试题失败");
        } else {
            return R.ok("添加试题成功", sysSubjectEntity);
        }
    }

    @ApiOperation(httpMethod = "POST",value = "删除试题",response = R.class,notes = "删除试题")
    @PostMapping("/delete")
    @Override
    public R del(Long id) {
        boolean del = sysSubjectService.del(id);
        if(del)
            return R.ok("删除成功");

        return R.error("删除失败");
    }

    @ApiOperation(httpMethod = "POST",value = "更新试卷信息",response = R.class,notes = "更新试卷信息")
    @PostMapping("/update")
    @Override
    public R update(@RequestBody SysSubjectEntity sysSubjectEntity) {
        boolean update = sysSubjectService.update(sysSubjectEntity);
        if(update) {
            return R.ok("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    @ApiOperation(httpMethod = "GET", value = "获取所有试卷信息", response = R.class,
            notes = "获取所有试卷信息")
    @GetMapping("/all")
    @Override
    public R getAll() {
        return R.ok("获取成功",sysSubjectService.getAll());
    }

    @Override
    public R get(SysSubjectEntity sysSubjectEntity) {
        return null;
    }

    @Override
    public R page(Map<String, Object> map) {
        return null;
    }

    @ApiOperation(httpMethod = "GET", value = "根据类型获取试卷", response = R.class,
            notes = "根据类型获取试卷")
    @GetMapping("/type/{typeId}")
    public R getSubjectByType(@PathVariable("typeId")long typeId){
        List<SysSubjectEntity> sysSubjectEntities = sysSubjectService.getAllByTypeId(typeId);
        System.out.println(sysSubjectEntities);
        if(sysSubjectEntities!=null&&sysSubjectEntities.size()>0){
            return R.ok("获取成功",sysSubjectEntities);
        }
        return R.ok("数据为null",null);
    }

    @ApiOperation(httpMethod = "GET", value = "like查询", response = R.class,
            notes = "like查询")
    @GetMapping("/search/{key}")
    public R findSysSubjectEntitiesByTitle(@PathVariable("key")String key){
        List<SysSubjectEntity> sysSubjectEntitiesByTitle = sysSubjectService.findSysSubjectEntitiesByTitle(key);
        List<SysSubjectEntity> myList = sysSubjectEntitiesByTitle.stream().distinct().collect(Collectors.toList());

        return R.ok("获取成功",myList);
    }
}
