package com.itheima.admin.test;

import com.itheima.admin.AdminApp;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.service.ModuleService;
import com.itheima.admin.vo.ModuleVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdminApp.class)
class ModuleServiceTest {
    @Autowired
    private ModuleService moduleService;

    @Test
    void queryChildModule() {
//        Module module=new Module();
//        module.setModuleId("1");
//        ModuleVo moduleVo=moduleService.queryChildModule(module);
//        System.out.println(moduleVo);
    }
}
