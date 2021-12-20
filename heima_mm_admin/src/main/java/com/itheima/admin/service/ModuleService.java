package com.itheima.admin.service;


import com.itheima.PageVo;
import com.itheima.admin.dto.ModuleDto;
import com.itheima.admin.dto.ModulePageDto;
import com.itheima.admin.vo.ModulePageVo;
import com.itheima.admin.vo.ModuleTreeVo;
import com.itheima.admin.vo.ModuleVo;

import java.util.List;

public interface ModuleService {
    PageVo<ModulePageVo> queryByPage(ModulePageDto modulePageDto);

    public List<ModuleTreeVo> queryAllRootModule();
    public ModuleTreeVo queryChildModule(ModuleTreeVo moduleTreeVo);
    Boolean add(ModuleDto moduleDto);

    Boolean delete(Integer id);

    ModuleVo queryById(Integer id);

    Boolean edit(ModuleVo moduleVo);
}
