package com.itheima.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleModuleDto {
    private String roleId;
    private List<String> moduleIds;
}
