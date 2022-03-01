package com.itheima.admin.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.ModuleDto;
import com.itheima.admin.service.IModuleService;
import com.itheima.admin.vo.ModuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
@Api(value = "管理后台模块模块接口", tags = "admin_user", description = "用于实现后台模块的增删改查操作")
public class ModuleController {

    @Autowired
    private IModuleService moduleService;

    @PostMapping("/list")
    @ApiOperation("分页查询模块")
    public PageVo<ModuleVo> queryByPage(@RequestBody PageDto pageDto){
        return moduleService.queryByPage(pageDto);
    }




    @GetMapping("/listall")
    @ApiOperation("查询所有模块")
    public List<ModuleVo> queryAll(){
        return moduleService.queryAll();
    }

    @PostMapping("/add")
    @ApiOperation("添加模块")
    public Result addDept(@RequestBody ModuleDto moduleDto){
        return moduleService.addModule(moduleDto);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑模块")
    public Result updateDept(@RequestBody ModuleDto moduleDto){
        return moduleService.updateModule(moduleDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除模块")
    public Result deleteDept(@PathVariable("id") String id){
        return moduleService.deleteModule(id);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询模块")
    public ModuleVo queryById(@PathVariable("id") String id){
        return moduleService.queryById(id);
    }
}
