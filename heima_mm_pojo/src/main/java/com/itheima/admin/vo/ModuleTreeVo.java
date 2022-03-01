package com.itheima.admin.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModuleTreeVo {
    private String id;
    private String label;
    private List<ModuleTreeVo> children;



}
