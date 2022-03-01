package com.itheima.admin.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.ModuleDto;
import com.itheima.admin.vo.ModuleVo;

import java.util.List;

public interface IModuleService {
    PageVo<ModuleVo> queryByPage(PageDto pageDto);


    List<ModuleVo> queryAll();

    Result addModule(ModuleDto moduleDto);

    Result updateModule(ModuleDto moduleDto);

    Result deleteModule(String id);

    ModuleVo queryById(String id);
}
