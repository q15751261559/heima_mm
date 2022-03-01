package com.itheima.admin.dto;

import com.itheima.admin.pojo.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoleDto {

    private String roleId;
    private String name;
    private String remark;
    private String roleDesc;

    public Role toRole(){
        Role role = new Role();
        BeanUtils.copyProperties(this,role);
        return role;
    }
}
