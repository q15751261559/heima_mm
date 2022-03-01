package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from ss_user where user_id = #{id}")
    User selectById(String id);

    @Delete("delete from ss_user where user_id = #{id}")
    boolean deleteById(String id);

    List<User> selectByPage(@Param("state") String status,
                            @Param("username") String username);

    int addUserToRole(@Param("userId") String userId,
                      @Param("roleIds") List<Object> roleIds);

    @Select("select role_id from ss_role_user where user_id = #{id}")
    List<String> selectRoleIdsByUserId(String id);
}
