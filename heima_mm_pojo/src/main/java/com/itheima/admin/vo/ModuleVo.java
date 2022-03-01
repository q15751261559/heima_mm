package com.itheima.admin.vo;

import lombok.Data;

@Data
public class ModuleVo {
    private String moduleId;
    private String  name;
    private String  parentId;
    private Integer  ctype;
    private Integer  state;
    private String  curl;
    private String  remark;
}
