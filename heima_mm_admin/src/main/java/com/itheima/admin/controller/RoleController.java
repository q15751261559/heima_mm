package com.itheima.admin.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
@Api(value = "管理后台橘色模块接口", tags = "admin_user", description = "用于实现后台角色的增删改查操作")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/list")
    @ApiOperation("分页查询角色列表")
    public PageVo<RoleVo> queryByPage(@RequestBody PageDto pageDto){
        return roleService.queryByPage(pageDto);
    }

    @GetMapping("/listall")
    @ApiOperation("查询所有角色列表")
    public List<RoleVo> queryAll(){
        return roleService.queryAll();
    }

    @PostMapping("/add")
    @ApiOperation("添加角色")
    public Result addRole(@RequestBody RoleDto roleDto){
        boolean result = roleService.addRole(roleDto);
        return new Result(result,result?"新增角色成功":"新增角色失败",null);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询角色")
    public RoleVo getRole(@PathVariable("id") String id){
        RoleVo roleVo = roleService.queryById(id);
        return roleVo;
    }

    @PutMapping("/edit")
    @ApiOperation("编辑角色")
    public Result editRole(@RequestBody RoleDto roleDto){
        boolean result = roleService.editRole(roleDto);
        return new Result(result,result?"编辑角色成功":"编辑角色失败",null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    public Result deleteRole(@PathVariable("id") String id){
        boolean result = roleService.deleteRole(id);
        return new Result(result,result?"删除角色成功":"删除角色失败",null);
    }

    @PostMapping("/auth")
    @ApiOperation("角色授权")
    public Result roleAuth(Map map){
        boolean result = roleService.roleAuth(map);
        return new Result(result,result?"角色授权成功":"角色授权失败",null);
    }


    @GetMapping("/{id}/modules")
    public Result getModuleIds(@PathVariable("id") String id){
        List<String> ids = roleService.queryModuleIds(id);
        return new Result(true,"查询成功",ids);
    }


}
