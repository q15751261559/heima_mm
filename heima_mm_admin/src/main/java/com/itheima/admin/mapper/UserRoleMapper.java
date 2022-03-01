package com.itheima.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

    @Delete("delete from ss_role_user where user_id = #{id}")
    void deleteByUserId(String id);
}
