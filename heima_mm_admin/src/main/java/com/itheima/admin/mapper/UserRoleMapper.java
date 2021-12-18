package com.itheima.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RestController;

@Mapper
@RestController
public interface UserRoleMapper {

    @Delete("delete from ss_role_user where user_id = #{id}")
    void deleteByUserId(String id);
}
