package com.itheima.admin.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "管理后台用户模块接口", tags = "admin_user", description = "用于实现后台用户的增删改查操作")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户")
    public UserVo queryById(@PathVariable("id") String id){
        return userService.queryById(id);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除用户")
    public Result deleteById(@PathVariable("id") String id){
        boolean result = userService.deleteById(id);
        return new Result(result,result?"删除用户成功":"删除用户失败",null);
    }

    @PostMapping("/list")
    @ApiOperation("分页查询用户列表数据")
    public PageVo<UserPageVo> queryByPage(@RequestBody PageDto pageDto){
        return userService.queryByPage(pageDto);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户数据")
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }


    @PutMapping("/edit")
    @ApiOperation("编辑用户")
    public Result updateUser(@RequestBody UserDto userDto){
        boolean result = userService.updateUser(userDto);
        return new Result(result,result?"修改用户成功":"修改用户失败",null);
    }

    @PostMapping("/role")
    @ApiOperation("用户授权")
    public Result userToRole(@RequestBody Map map){
        String userId = (String) map.get("userId");
        List<Object> roleIds = (List<Object>) map.get("roleIds");
        int rs = userService.userToRole(userId,roleIds);
        return new Result(rs>0,rs>0?"用户授权成功":"用户授权失败",null);
    }

    @GetMapping("/{id}/role")
    public Result queryRoleByUserId(@PathVariable("id") String id){
        return userService.queryRoleByUserId(id);
    }
}
