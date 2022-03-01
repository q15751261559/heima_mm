package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

//类名=表名   属性名=列名
@Data
@TableName("ss_role")
public class Role {
  @TableId(type = IdType.ASSIGN_UUID)
  private String roleId;
  private String name;
  private String remark;
  private Integer orderNo;
  private String createBy;
  private String createDept;
  private String updateBy;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  private String roleDesc;

}
