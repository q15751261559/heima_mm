package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    int deleteRoleUser(@Param("roleId") String roleId);
    int deleteRoleModule(@Param("roleId") String roleId);

    int addRoleToModule(@Param("roleId") String roleId,
                        @Param("modulesIds") List<String> moduleIds);

    @Select("select module_id from ss_role_module where role_id = #{id}")
    List<String> selectModuleIds(String id);
}
