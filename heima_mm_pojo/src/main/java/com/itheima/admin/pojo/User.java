package com.itheima.admin.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
  private String userId;
  private String email;
  private String userName;
  private String station;
  private String password;
  private String state;
  private String managerId;
  private String gender;
  private String telephone;
  private String birthday;
  private Integer degree;
  private Integer salary;
  private String joinDate;
  private Integer orderNo;
  private String remark;
  private String deptId;
  private String deptName;
  private String createBy;
  private String create_dept;
  private Date createTime;
  private String updateBy;
  private Date updateTime;
}
