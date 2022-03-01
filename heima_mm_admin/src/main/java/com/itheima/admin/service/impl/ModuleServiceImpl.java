package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.ModuleDto;
import com.itheima.admin.mapper.ModuleMapper;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.IModuleService;
import com.itheima.admin.vo.ModuleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public PageVo<ModuleVo> queryByPage(PageDto pageDto) {
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        if(pageDto.getQueryString()!=null && pageDto.getQueryString() != ""){
            queryWrapper.like("name",pageDto.getQueryString());
        }
        IPage<Module> page = new Page<>(pageDto.getCurrentPage(),pageDto.getPageSize());
        IPage<Module> moduleIPage = moduleMapper.selectPage(page, queryWrapper);
        List<ModuleVo> collect = getModuleVos(moduleIPage.getRecords());
        return new PageVo<ModuleVo>(collect, (int) moduleIPage.getTotal());
    }

    private List<ModuleVo> getModuleVos(List<Module> moduleList) {
        return moduleList.stream().map(module -> {
                ModuleVo moduleVo = new ModuleVo();
                BeanUtils.copyProperties(module, moduleVo);
                return moduleVo;
            }).collect(Collectors.toList());
    }


    @Override
    public List<ModuleVo> queryAll() {
        List<Module> modules = moduleMapper.selectList(null);
        List<ModuleVo> moduleVos = getModuleVos(modules);
        return moduleVos;
    }



    @Override
    public Result addModule(ModuleDto moduleDto) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleDto,module);
        int insert = moduleMapper.insert(module);
        return new Result(insert>0,insert>0?"添加成功":"添加失败",null);
    }

    @Override
    public Result updateModule(ModuleDto moduleDto) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleDto,module);
        int insert = moduleMapper.updateById(module);
        return new Result(insert>0,insert>0?"修改成功":"修改失败",null);
    }

    @Override
    public Result deleteModule(String id) {
        int i = moduleMapper.deleteById(id);
        return new Result(i>0,i>0?"删除成功":"删除失败",null);
    }

    @Override
    public ModuleVo queryById(String id) {
        Module module = moduleMapper.selectById(id);
        ModuleVo modeleVo = new ModuleVo();
        BeanUtils.copyProperties(module,modeleVo);
        return modeleVo;
    }
}
