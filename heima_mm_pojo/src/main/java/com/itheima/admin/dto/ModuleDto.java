package com.itheima.admin.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModuleDto {
    private String name;
    private String parentId;
    private BigDecimal ctype;
    private BigDecimal state;
    private String curl;
    private String remark;
}
