package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.admin.vo.ModuleTreeVo;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("ss_module")
public class Module {
    @TableId(value = "module_id", type = IdType.ASSIGN_UUID)
    private String moduleId;
    private String name;
    private String parentId;
    private Integer ctype;
    private Integer state;
    private String curl;
    private String remark;
    private String parentName;
    private Integer isLeaf;
    private String ico;
    private String cpermission;
    private String belong;
    private Integer orderNo;



    public ModuleTreeVo toModuleVo(){
        ModuleTreeVo moduleTreeVo =new ModuleTreeVo();
        moduleTreeVo.setId(this.getModuleId());
        moduleTreeVo.setLabel(this.getName());
        return moduleTreeVo;
    }}
