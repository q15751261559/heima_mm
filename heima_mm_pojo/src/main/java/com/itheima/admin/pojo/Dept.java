package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ss_dept")
public class Dept {
    @TableId
    private String deptId;
    private String deptName;
    private String parentId;
    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
