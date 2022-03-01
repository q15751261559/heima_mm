package com.itheima.question.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CatalogDto;
import com.itheima.question.vo.CatalogVo;

import java.util.List;

public interface ICatalogService {
    PageVo<CatalogVo> queryByPage(PageDto pageDto);

    List<CatalogVo> queryAll();

    Result addCatalog(CatalogDto catalogDto);

    Result updateCatalog(CatalogDto catalogDto);

    Result deleteCatalog(String id);

    CatalogVo queryById(String id);
}
