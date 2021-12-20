package com.itheima.admin.controller;

import com.itheima.admin.service.ModuleService;
import com.itheima.admin.vo.ModuleTreeVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/module")
@Api(value = "模块操作",tags = "Module",description = "模块功能API")
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @GetMapping("/listall")
    public List<ModuleTreeVo> queryAllModule(){
        return moduleService.queryAllRootModule();
    }

    @GetMapping("/list")
    public ModuleTreeVo queryModule(){
        ModuleTreeVo moduleTreeVo =new ModuleTreeVo();
        moduleTreeVo.setId("1");
        ModuleTreeVo moduleTreeVo1 =moduleService.queryChildModule(moduleTreeVo);
        System.out.println(moduleTreeVo1.getChildren());
        return moduleTreeVo1;
    }

}
