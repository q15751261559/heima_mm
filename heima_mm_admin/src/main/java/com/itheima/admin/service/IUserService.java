package com.itheima.admin.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;

import java.util.List;

public interface IUserService {
    UserVo queryById(String id);

    boolean deleteById(String id);

    PageVo<UserPageVo> queryByPage(PageDto pageDto);

    Result addUser(UserDto userDto);

    boolean updateUser(UserDto userDto);

    int userToRole(String userId, List<Object> roleIds);

    Result queryRoleByUserId(String id);
}
