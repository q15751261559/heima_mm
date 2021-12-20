package com.itheima.security_demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Data
@TableName("ss_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable,UserDetails {
  @TableId("user_id")
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


  @TableField(exist=false)
  private Collection<GrantedAuthority> autorities;

  public User(String username, String password, Collection<GrantedAuthority> autorities) {
    this.userName=username;
    this.password=password;
    this.autorities=autorities;
  }

  public User() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  //账号没有过期
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  //没有被锁定
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  //密码没有过期
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  //是否可用
  public boolean isEnabled() {
    return true;
  }


}
