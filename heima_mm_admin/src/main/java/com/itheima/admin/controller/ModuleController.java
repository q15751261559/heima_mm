package com.itheima.admin.controller;

import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.ModuleService;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.ModuleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/module")
@Api(value = "模块操作",tags = "Module",description = "模块功能API")
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @GetMapping("/listall")
    public List<ModuleVo> queryAllModule(){
        return moduleService.queryAllRootModule();
    }

    @GetMapping("/list")
    public ModuleVo queryModule(){
        ModuleVo moduleVo=new ModuleVo();
        moduleVo.setId("1");
        ModuleVo moduleVo1=moduleService.queryChildModule( moduleVo);
        System.out.println(moduleVo1.getChildren());
        return moduleVo1;
    }

}
