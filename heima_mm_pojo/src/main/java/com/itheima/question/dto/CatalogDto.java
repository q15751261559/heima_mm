package com.itheima.question.dto;

import com.itheima.question.pojo.Catalog;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CatalogDto {
    private String id;
    private String name;
    private String state;
    private String remark;
    private String courseId;

    public Catalog toCatalog() {
        Catalog catalog = new Catalog();
        BeanUtils.copyProperties(this,catalog);
        return catalog;
    }
}
