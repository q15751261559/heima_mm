package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.admin.mapper.ModuleMapper;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.ModuleService;
import com.itheima.admin.vo.ModuleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;
    @Override
    public List<ModuleVo> queryAllRootModule() {
        List<Module> modules=moduleMapper.selectList(new QueryWrapper<Module>().isNull("parent_id"));
        List<ModuleVo> moduleVos=new ArrayList<>();
        for (Module module1:modules)
        {
            moduleVos.add(queryChildModule(module1.toModuleVo()));
        }
        return moduleVos;
    }



    public ModuleVo queryChildModule(ModuleVo moduleVo){
        moduleVo.setChildren(new ArrayList<>());
        List<Module> modules=moduleMapper.selectList(new QueryWrapper<Module>().eq("parent_id",moduleVo.getId()));
        for (Module module1:modules)
            {
                ModuleVo moduleVo1=module1.toModuleVo();
                moduleVo.addChildren(moduleVo1);
                queryChildModule(moduleVo1);
            }
        return moduleVo;
    }
}
