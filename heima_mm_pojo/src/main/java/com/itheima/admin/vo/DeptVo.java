package com.itheima.admin.vo;

import com.itheima.admin.pojo.Dept;
import lombok.Data;

@Data
public class DeptVo {
    private String deptId;
    private String deptName;
    private String parentName;
    private String state;

    public DeptVo(Dept dept)
    {
        this.deptId=dept.getDeptId();
        this.deptName=dept.getDeptName();
        this.parentName=dept.getParentId();
        this.state= String.valueOf(dept.getState());
    }
}
