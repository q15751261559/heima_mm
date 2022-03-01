package com.itheima.admin.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.admin.dto.RoleDto;
import com.itheima.admin.vo.RoleVo;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    PageVo<RoleVo> queryByPage(PageDto pageDto);


    List<RoleVo> queryAll();


    boolean addRole(RoleDto roleDto);

    boolean deleteRole(String id);

    boolean roleAuth(Map map);

    RoleVo queryById(String id);

    boolean editRole(RoleDto roleDto);

    List<String> queryModuleIds(String id);
}
