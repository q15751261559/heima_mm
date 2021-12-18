package com.itheima.admin.controller;

import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(value = "用户操作",tags = "User",description = "用户功能API")
public class UserController {


    @Autowired
    private IUserService userService;


    @ApiOperation("根据ID查询用户")
    @GetMapping("/{id}")
    public UserVo queryById(@PathVariable("id") String id){
        return userService.queryById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id){
        boolean result = userService.deleteById(id);
        return new Result(result,result?"删除用户成功":"删除用户失败",null);
    }

    @PostMapping("/list")
    public PageVo<UserPageVo> queryByPage(@RequestBody UserPageDto userPageDto){
        if(userPageDto.getCurrentPage()==null){
            userPageDto.setCurrentPage(1);
        }
        if(userPageDto.getPageSize()==null){
            userPageDto.setPageSize(10);
        }
        return userService.queryByPage(userPageDto);
    }

    @PostMapping("/add")
    public Result addUser(@RequestBody UserDto userDto){
        boolean result=userService.addUser(userDto);
        return new Result(result,result?"新增用户成功":"新增用户失败",null);
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody UserDto userDto)
    {
        boolean result=userService.updateUser(userDto);
        return new Result(result,result?"修改用户成功":"修改用户失败",null);
    }

    @PostMapping("/role")
    public Result roleUser(@RequestBody UserRoleDto userRoleDto)
    {
        System.out.println(userRoleDto.getRoles());
        boolean result=userService.roleUser(userRoleDto);
        return new Result(result,result?"用户授权成功":"用户授权失败",null);
    }
}

