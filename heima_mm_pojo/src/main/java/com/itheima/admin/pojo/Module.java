package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ss_module")
public class Module {
    @TableId(type = IdType.ASSIGN_UUID)
    private String moduleId;
    private String parentId;
    private String parentName;
    private String name;
    private String isLeaf;
    private String ico;
    private String cpermission;
    private String curl;
    private String ctype;
    private String state;
    private String belong;
    private String remark;
    private String orderNo;
}
