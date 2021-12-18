package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ss_dept")
public class Dept {
    @TableId("dept_id")
    private String deptId;
    private String deptName;
    private String parentId;
    private String state;
}
