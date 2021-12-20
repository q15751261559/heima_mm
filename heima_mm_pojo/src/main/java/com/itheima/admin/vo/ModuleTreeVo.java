package com.itheima.admin.vo;

import com.itheima.admin.pojo.Module;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModuleTreeVo {
    private String id;
    private String label;
    private List<ModuleTreeVo> children;


    public Module toModule(){
        Module module=new Module();
        BeanUtils.copyProperties(this,module);
        return module;
    }
    public void addChildren(ModuleTreeVo moduleTreeVo)
    {
        if (this.children==null)
        {
            this.children=new ArrayList<>();
        }
        this.children.add(moduleTreeVo);
    }

    public static void main(String[] args) {
//        ModuleVo moduleVo=new ModuleVo();
//        moduleVo.setName("石振宇");
//        moduleVo.setChildren();
//        ModuleVo moduleVo1=new ModuleVo();
//        moduleVo1.setName("李俊杰");
//        moduleVo.addChildren(moduleVo1);
    }
}
