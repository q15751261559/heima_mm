package com.itheima.admin.dto;

import lombok.Data;

@Data
public class DeptPageDto {
    private Integer currentPage;
    private Integer pageSize;
    private String deptName;
    private String status;
}
