package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Value;

import java.util.Date;
//类名=表名   属性名=列名
@Data
@TableName("ss_role")
public class Role {
  @TableId("role_id")
  private String roleId;
  private String name;
  private String remark;
  private Integer orderNo;
  private String createBy;
  private String createDept;
  @TableField(fill= FieldFill.INSERT)
  private Date createTime;
  private String updateBy;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
