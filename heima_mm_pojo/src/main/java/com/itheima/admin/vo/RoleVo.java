package com.itheima.admin.vo;

import lombok.Data;

import java.util.Date;


@Data
public class RoleVo {
    /*
        "roleId": "c4191603-c4b2-40dd-a03f-256cb3bd8844"
		"name": "超级管理员",
		"remark": "666",
		"createTime": "2021-02-02"
    */
    private String roleId;
    private String name;
    private String remark;
    private String createTime;
}
