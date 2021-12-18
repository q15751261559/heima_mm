package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.admin.vo.ModuleVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
@TableName("ss_module")
public class Module {
    @TableId(value = "module_id", type = IdType.ASSIGN_UUID)
    private String moduleId;
    private String name;
    private String parentId;
    private BigDecimal ctype;
    private BigDecimal state;
    private String curl;
    private String remark;
    private String parentName;
    private BigDecimal isLeaf;
    private String ico;
    private String cpermission;
    private String belong;
    private BigDecimal orderNo;



    public ModuleVo toModuleVo(){
        ModuleVo moduleVo=new ModuleVo();
        moduleVo.setId(this.getModuleId());
        moduleVo.setLabel(this.getName());
        return moduleVo;
    }}
