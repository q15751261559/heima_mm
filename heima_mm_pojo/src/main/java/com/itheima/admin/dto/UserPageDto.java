package com.itheima.admin.dto;

import lombok.Data;

@Data
public class UserPageDto {
    private Integer currentPage;
    private Integer pageSize;
    private String username;
    private String status;
}
