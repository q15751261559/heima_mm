package com.itheima.question.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.question.vo.CatalogVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@Data
@TableName("st_catalog")
public class Catalog {
    @TableId
    private String id;
    private String name;
    private String state;
    private String remark;
    private String courseId;

    private Integer orderNo;
    private String createBy;
    private String createDept;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String courseName;

    public CatalogVo toCatalogVo() {
        CatalogVo catalogVo = new CatalogVo();
        BeanUtils.copyProperties(this,catalogVo);
        return catalogVo;
    }
}
