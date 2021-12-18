package com.itheima.admin.mapper;


import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.itheima.Result;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@RestController
public interface UserMapper {
    @Select("select * from ss_user where user_id = #{id}")
    User selectById(String id);

    @Delete("delete from ss_user where user_id = #{id}")
    boolean deleteById(String id);

    List<User> selectByPage(@Param("state") String status, @Param("username") String username);

    boolean addUser(User user);

    boolean updateUser(User user);
    boolean roleUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
