package com.itheima.admin.dto;

import lombok.Data;

@Data
public class DeptDto {
    private String deptId;
    private String deptName;
    private String parentId;
    private String state;
}
