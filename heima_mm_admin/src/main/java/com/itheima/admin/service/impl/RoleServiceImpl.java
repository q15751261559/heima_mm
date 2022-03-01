package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.mapper.RoleMapper;
import com.itheima.admin.pojo.Role;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public PageVo<RoleVo> queryByPage(PageDto pageDto) {

        IPage<Role> page = new Page<>(pageDto.getCurrentPage(),pageDto.getPageSize());

        QueryWrapper<Role> queryWrapper = null;
        if(pageDto.getQueryString() != null){
            queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name",pageDto.getQueryString());
        }

        IPage<Role> roleIPage = roleMapper.selectPage(page, queryWrapper);


        List<RoleVo> collect = getRoleVos(roleIPage.getRecords());
        return new PageVo<RoleVo>(collect,(int)roleIPage.getTotal());
    }

    private List<RoleVo> getRoleVos(List<Role> roleIPage) {
        if(roleIPage==null){
            return new ArrayList<>();
        }
        return roleIPage.stream().map(
                role -> {
                    RoleVo roleVo = new RoleVo();
                    BeanUtils.copyProperties(role, roleVo);
                    Date createTime = role.getCreateTime();
                    roleVo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(createTime));
                    return roleVo;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<RoleVo> queryAll() {
        List<Role> roles = roleMapper.selectList(null);
        return getRoleVos(roles);
    }

    @Override
    public boolean addRole(RoleDto roleDto) {
        int insert = roleMapper.insert(roleDto.toRole());
        return insert>0;
    }

    @Override
    public boolean deleteRole(String id) {
        //角色和用户
        roleMapper.deleteRoleUser(id);
        //角色和模块
        roleMapper.deleteRoleModule(id);
        //角色
        int i = roleMapper.deleteById(id);
        return i>0;
    }

    @Override
    public boolean roleAuth(Map map) {
        String roleId = (String) map.get("roleId");
        List<String> moduleIds = (List<String>) map.get("moduleIds");
        int result = roleMapper.addRoleToModule(roleId,moduleIds);
        return result!=0;
    }

    @Override
    public RoleVo queryById(String id) {
        Role role = roleMapper.selectById(id);
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role,roleVo);
        return roleVo;
    }

    @Override
    public boolean editRole(RoleDto roleDto) {
        int insert = roleMapper.updateById(roleDto.toRole());
        return insert>0;
    }

    @Override
    public List<String> queryModuleIds(String id) {
        return roleMapper.selectModuleIds(id);
    }
}
