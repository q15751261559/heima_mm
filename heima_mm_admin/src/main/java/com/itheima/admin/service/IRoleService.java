package com.itheima.admin.service;

import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.dto.RoleModuleDto;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.RoleModuleVo;
import com.itheima.admin.vo.RoleVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRoleService {
    boolean addRole(RoleDto roleDto);
    boolean deleteRole(String id);
    public List<RoleVo> queryAllRole();
    public RoleVo queryById(String id);
    boolean updateRole(RoleDto roleDto);
    public RoleModuleVo queryRoleModules(String id);
    public boolean userAuthRole(RoleModuleDto roleModuleDto);

}
