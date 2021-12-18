package com.itheima.admin.service;

import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.RoleModuleDto;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {
    UserVo queryById(String id);

    boolean deleteById(String id);

    PageVo<UserPageVo> queryByPage(UserPageDto userPageDto);
    boolean addUser(UserDto userDto);

    boolean updateUser(UserDto userDto);
    boolean roleUser(UserRoleDto userRoleDto);
}
