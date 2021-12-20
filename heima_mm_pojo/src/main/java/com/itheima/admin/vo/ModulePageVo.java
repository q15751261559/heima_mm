package com.itheima.admin.vo;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/17
 * @Description:
 */
@Data
public class ModulePageVo {
    private String moduleId;
    private String name;
    private String ctype;
    private String parentName;
    private String curl;
    private String state;
}
