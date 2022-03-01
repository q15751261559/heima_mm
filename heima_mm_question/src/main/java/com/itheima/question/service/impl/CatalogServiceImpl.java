package com.itheima.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CatalogDto;
import com.itheima.question.mapper.CatalogMapper;
import com.itheima.question.pojo.Catalog;
import com.itheima.question.service.ICatalogService;
import com.itheima.question.vo.CatalogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public PageVo<CatalogVo> queryByPage(PageDto pageDto) {
        IPage<Catalog> page = new Page(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<Catalog> queryWrapper= new QueryWrapper<>();
        if(pageDto.getQueryString()!=null){
            queryWrapper.like("name",pageDto.getQueryString());
        }
        if(pageDto.getStatus()!=null){
            queryWrapper.eq("state",pageDto.getStatus());
        }
        IPage<Catalog> deptIPage = catalogMapper.selectPage(page, queryWrapper);
        List<CatalogVo> collect = getCatalogVos(deptIPage.getRecords());
        return new PageVo<CatalogVo>(collect, (int) deptIPage.getTotal());
    }

    @Override
    public List<CatalogVo> queryAll() {
        List<Catalog> catalogs = catalogMapper.selectList(null);

        List<CatalogVo> catalogVos = getCatalogVos(catalogs);

        return catalogVos;
    }

    private List<CatalogVo> getCatalogVos(List<Catalog> catalogs) {
        List<CatalogVo> collect = catalogs.stream().map(catalog -> {
            CatalogVo catalogVo = new CatalogVo();
            BeanUtils.copyProperties(catalog, catalogVo);
            return catalogVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Result addCatalog(CatalogDto catalogDto) {
        int insert = catalogMapper.insert(catalogDto.toCatalog());
        return new Result(insert>0,insert>0?"新增成功":"新增失败",null );
    }

    @Override
    public Result updateCatalog(CatalogDto catalogDto) {
        int insert = catalogMapper.updateById(catalogDto.toCatalog());
        return new Result(insert>0,insert>0?"修改成功":"修改失败",null );
    }

    @Override
    public Result deleteCatalog(String id) {
        int insert = catalogMapper.deleteById(id);
        return new Result(insert>0,insert>0?"删除成功":"删除失败",null );
    }

    @Override
    public CatalogVo queryById(String id) {
        Catalog catalog = catalogMapper.selectById(id);
        return catalog.toCatalogVo();
    }
}
