package com.itheima.admin.controller;

import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.dto.RoleModuleDto;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.RoleModuleVo;
import com.itheima.admin.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "角色操作",tags = "Role",description = "角色功能API")
public class RoleController {
    @Autowired
    IRoleService roleService;
    @ApiOperation("新增角色")
    @PostMapping("/add")
    public Result addRole(@RequestBody RoleDto roleDto){
        boolean result=roleService.addRole(roleDto);
        return new Result(result,result?"新增部门成功":"新增部门失败",null);
    }

    @ApiOperation("查询所有角色")
    @GetMapping("/listall")
    public List<RoleVo> queryAllRole(){
        return roleService.queryAllRole();
    }

    @ApiOperation("根据ID查询角色")
    @GetMapping("/{id}")
    public RoleVo queryById(@PathVariable("id") String id){
        return roleService.queryById(id);
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    public Result deleteById(@PathVariable("id") String id){
        boolean result = roleService.deleteRole(id);
        return new Result(result,result?"删除部门成功":"删除部门失败",null);
    }

    @PutMapping("/update")
    public Result updateRole(@RequestBody RoleDto roleDto)
    {
        boolean result=roleService.updateRole(roleDto);
        return new Result(result,result?"修改部门成功":"修改部门失败",null);
    }

    @ApiOperation("查询角色对应模块")
    @GetMapping("/role/{id}/modules")
    public RoleModuleVo queryRoleModules(@PathVariable("id") String id)
    {
        return roleService.queryRoleModules(id);
    }

    @ApiOperation("给用户授予对应的角色")
    @PostMapping("/role/auth")
    public Result userAuthRole(@RequestBody RoleModuleDto roleModuleDto)
    {
        boolean result=roleService.userAuthRole(roleModuleDto);
        return new Result(result,result?"角色授权成功":"角色授权失败失败",null);
    }
}
