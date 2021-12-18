package com.itheima.admin.service.impl;

import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.dto.RoleModuleDto;
import com.itheima.admin.mapper.RoleMapper;
import com.itheima.admin.pojo.Role;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.RoleModuleVo;
import com.itheima.admin.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public boolean addRole(RoleDto roleDto) {
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        roleDto.setRoleId(uuid);
        Role role=new Role();
        BeanUtils.copyProperties(roleDto,role);
        int i=roleMapper.insert(role);
        return i!=0;
    }

    @Override
    public boolean deleteRole(String id) {
        roleMapper.deleteRoleModule(id);
        roleMapper.deleteRoleUser(id);
        Role role=new Role();
        role.setRoleId(id);
        int i=roleMapper.deleteById(role);
        return i!=0;
    }

    @Override
    public List<RoleVo> queryAllRole() {
        List<Role> roles=roleMapper.selectList(null);
        List<RoleVo> roleVos=new ArrayList<>();
        if(roles!=null){
            for (Role role:roles)
            {
                RoleVo roleVo=new RoleVo();
                BeanUtils.copyProperties(role,roleVo);
                roleVos.add(roleVo);
            }
        }
        return roleVos;
    }

    @Override
    public RoleVo queryById(String id) {
        Role role = roleMapper.selectById(id);
        RoleVo roleVo=new RoleVo();
        if(role!=null){
            BeanUtils.copyProperties(role,roleVo);
        }
        return roleVo;
    }

    @Override
    public boolean updateRole(RoleDto roleDto) {
        Role role = new Role();
        if(roleDto!=null){
            BeanUtils.copyProperties(roleDto,role);
        }
        return roleMapper.updateById(role)!=0;
    }

    @Override
    public RoleModuleVo queryRoleModules(String id) {
        RoleModuleVo roleModuleVo=new RoleModuleVo();
        roleModuleVo.setFlag(false);
        roleModuleVo.setMessage("查询角色对应模块失败");
        roleModuleVo.setData(roleMapper.queryRoleModules(id));
        if (roleModuleVo.getData().size()!=0)
        {
            roleModuleVo.setFlag(true);
            roleModuleVo.setMessage("查询角色对应模块成功");
        }
        return roleModuleVo;
    }

    @Override
    public boolean userAuthRole(RoleModuleDto roleModuleDto) {
        return roleMapper.userAuthRole(roleModuleDto.getRoleId(),roleModuleDto.getModuleIds())!=0;
    }
}
