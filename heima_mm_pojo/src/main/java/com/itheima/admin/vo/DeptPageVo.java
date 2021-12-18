package com.itheima.admin.vo;

import lombok.Data;

@Data
public class DeptPageVo {
    private String deptId;
    private String deptName;
    private String parentId;
    private String state;
}
