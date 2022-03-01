package com.itheima.admin.dto;

import lombok.Data;

@Data
public class ModuleDto {
    private String moduleId;
    private String  name;
    private String  parentId;
    private Integer  ctype;
    private Integer  state;
    private String  curl;
    private String  remark;
}
