package com.itheima;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/21
 * @Description:
 */
@Data
public class PageDto {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
    private String status;

}
