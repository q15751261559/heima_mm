package com.itheima.admin.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.service.IDeptService;
import com.itheima.admin.vo.DeptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Api(value = "管理后台部门模块接口", tags = "admin_user", description = "用于实现后台部门的增删改查操作")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @PostMapping("/list")
    @ApiOperation("分页查询部门")
    public PageVo<DeptVo> queryByPage(@RequestBody PageDto pageDto){
        return deptService.queryByPage(pageDto);
    }

    @GetMapping("/listall")
    @ApiOperation("查询所有部门")
    public List<DeptVo> queryAll(){
        List<DeptVo> deptVo=deptService.queryAll();
        return deptVo;
    }


    @PostMapping("/add")
    @ApiOperation("添加部门")
    public Result addDept(@RequestBody DeptDto deptDto){
        return deptService.addDept(deptDto);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑部门")
    public Result updateDept(@RequestBody DeptDto deptDto){
        return deptService.updateDept(deptDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除部门")
    public Result deleteDept(@PathVariable("id") String id){
        return deptService.deleteDept(id);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询部门")
    public DeptVo queryById(@PathVariable("id") String id){
        return deptService.queryById(id);
    }
}
