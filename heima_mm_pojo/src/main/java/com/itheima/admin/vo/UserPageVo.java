package com.itheima.admin.vo;

import lombok.Data;

@Data
public class UserPageVo {
    private String userId;
    private String email;
    private String userName;
    private String gender;
    private String status; //state
    private String deptName;
}
