package com.itheima.admin.service;


import com.itheima.admin.pojo.Module;
import com.itheima.admin.vo.ModuleVo;

import java.util.List;

public interface ModuleService {
    public List<ModuleVo> queryAllRootModule();
    public ModuleVo queryChildModule(ModuleVo moduleVo);
}
