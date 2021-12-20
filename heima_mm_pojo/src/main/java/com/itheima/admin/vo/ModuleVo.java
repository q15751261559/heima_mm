package com.itheima.admin.vo;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/19
 * @Description:
 */
@Data
public class ModuleVo {
    private String moduleId;
    private String name;
    private String parentId;
    private String ctype;
    private String state;
    private String curl;
    private String remark;
}
