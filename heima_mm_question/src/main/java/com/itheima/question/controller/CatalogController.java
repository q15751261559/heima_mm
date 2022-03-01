package com.itheima.question.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CatalogDto;
import com.itheima.question.service.ICatalogService;
import com.itheima.question.vo.CatalogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/catalog")
@Api(value = "管理后台题目分类模块接口", tags = "admin_user", description = "用于实现后台题目分类的增删改查操作")
public class CatalogController {

    @Autowired
    private ICatalogService catalogService;

    @PostMapping("/list")
    @ApiOperation("分页查询学科")
    public PageVo<CatalogVo> queryByPage(@RequestBody PageDto pageDto){
        return catalogService.queryByPage(pageDto);
    }

    @GetMapping("/listall")
    @ApiOperation("查询所有学科")
    public List<CatalogVo> queryAll(){
        return catalogService.queryAll();
    }

    @PostMapping("/add")
    @ApiOperation("添加学科")
    public Result addCatalog(@RequestBody CatalogDto catalogDto){
        return catalogService.addCatalog(catalogDto);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑学科")
    public Result updateCatalog(@RequestBody CatalogDto catalogDto){
        return catalogService.updateCatalog(catalogDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除学科")
    public Result deleteCatalog(@PathVariable("id") String id){
        return catalogService.deleteCatalog(id);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询学科")
    public CatalogVo queryById(@PathVariable("id") String id){
        return catalogService.queryById(id);
    }


}
