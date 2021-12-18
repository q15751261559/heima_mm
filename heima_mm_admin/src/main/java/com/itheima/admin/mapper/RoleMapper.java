package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@RestController
public interface RoleMapper extends BaseMapper<Role> {
    int deleteRoleUser(String roleId);
    int deleteRoleModule(String roleId);
    List<String> queryRoleModules(String id);
    int userAuthRole(@Param("roleId") String id,@Param("modules") List<String> modules);
}
