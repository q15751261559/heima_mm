package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.PageVo;
import com.itheima.admin.dto.ModuleDto;
import com.itheima.admin.dto.ModulePageDto;
import com.itheima.admin.mapper.ModuleMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.ModuleService;
import com.itheima.admin.vo.ModulePageVo;
import com.itheima.admin.vo.ModuleTreeVo;
import com.itheima.admin.vo.ModuleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;

    //分页
    @Override
    public PageVo<ModulePageVo> queryByPage(ModulePageDto modulePageDto) {
        PageHelper.startPage(modulePageDto.getCurrentPage(),modulePageDto.getPageSize());
        QueryWrapper<Module> wrapper=new QueryWrapper<>();
        if (modulePageDto.getName()!=null)
        {
            wrapper.like("name",modulePageDto.getName());
        }
        if (modulePageDto.getCtype()!=null){
            wrapper.eq("ctype",modulePageDto.getCtype());

        }
        List<Module> modules = moduleMapper.selectList(wrapper);
        PageInfo<Module> page=new PageInfo<>(modules);
        List<ModulePageVo> list=page.getList().stream().map(module -> {
            ModulePageVo modulePageVo=new ModulePageVo();
            BeanUtils.copyProperties(module,modulePageVo);
            String ctype=null;
            String state=null;
            switch (module.getCtype()){
                case 0:ctype="主菜单";
                    break;
                case 1:ctype="左侧菜单";
                    break;
                case 2:ctype="按钮";
                    break;
            }
            switch (module.getState()){
                case 0:state="未启用";
                    break;
                case 1:state="启用";
                    break;
            }
            modulePageVo.setCtype(ctype);
            modulePageVo.setState(state);
            return modulePageVo;
        }).collect(Collectors.toList());
        return new PageVo<ModulePageVo>(list, (int) page.getTotal());
    }

    @Override
    public List<ModuleTreeVo> queryAllRootModule() {
        List<Module> modules=moduleMapper.selectList(new QueryWrapper<Module>().isNull("parent_id"));
        List<ModuleTreeVo> moduleTreeVos =new ArrayList<>();
        for (Module module1:modules)
        {
            moduleTreeVos.add(queryChildModule(module1.toModuleVo()));
        }
        return moduleTreeVos;
    }



    public ModuleTreeVo queryChildModule(ModuleTreeVo moduleTreeVo){
        List<Module> modules=moduleMapper.selectList(new QueryWrapper<Module>().eq("parent_id", moduleTreeVo.getId()));
        for (Module module1:modules)
            {
                ModuleTreeVo moduleTreeVo1 =module1.toModuleVo();
                moduleTreeVo.addChildren(moduleTreeVo1);
                queryChildModule(moduleTreeVo1);
            }
        return moduleTreeVo;
    }

    @Override
    public Boolean add(ModuleDto moduleDto) {
        Module module=new Module();
        BeanUtils.copyProperties(moduleDto,module);
        int i = moduleMapper.insert(module);
        return i==1;
    }

    @Override
    public Boolean delete(Integer id) {
        int i = moduleMapper.deleteById(String.valueOf(id));
        return i==1;
    }

    @Override
    public ModuleVo queryById(Integer id) {
        ModuleVo moduleVo=new ModuleVo();
        Module module = moduleMapper.selectById(String.valueOf(id));
        BeanUtils.copyProperties(module,moduleVo);
        moduleVo.setCtype(String.valueOf(module.getCtype()));
        moduleVo.setState(String.valueOf(module.getState()));
        return moduleVo;
    }

    @Override
    public Boolean edit(ModuleVo moduleVo) {
        Module module=new Module();
        BeanUtils.copyProperties(moduleVo,module);
        int i = moduleMapper.updateById(module);
        return i==1;
    }

}
