package com.itheima.admin.dto;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/17
 * @Description:
 */
@Data
public class ModulePageDto {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private Integer ctype;
}
