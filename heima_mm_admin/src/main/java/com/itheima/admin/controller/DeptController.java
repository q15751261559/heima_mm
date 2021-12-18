package com.itheima.admin.controller;

import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.service.DeptService;
import com.itheima.admin.vo.DeptPageVo;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Api(value = "部门操作",tags = "Dept",description = "部门功能API")
public class DeptController {
    @Autowired
    DeptService deptService;
    @ApiOperation("查询所有部门")
    @GetMapping("/listall")
    public List<DeptVo> queryAllDept(){
        return deptService.queryAllDept();
    }

    @ApiOperation("根据ID查询部门")
    @GetMapping("/{id}")
    public DeptVo queryById(@PathVariable("id") String id){
        return deptService.queryById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除部门")
    public Result deleteById(@PathVariable("id") String id){
        boolean result = deptService.deleteById(id);
        return new Result(result,result?"删除部门成功":"删除部门失败",null);
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody DeptDto deptDto)
    {
        boolean result=deptService.updateDept(deptDto);
        return new Result(result,result?"修改部门成功":"修改部门失败",null);
    }

    @PostMapping("/add")
    @ApiOperation("新增部门")
    public Result addDept(@RequestBody DeptDto deptDto){
        boolean result=deptService.addDept(deptDto);
        return new Result(result,result?"新增部门成功":"新增部门失败",null);
    }

    @PostMapping("/list")
    public PageVo<DeptPageVo> queryByPage(@RequestBody DeptPageDto deptPageDto){
        if(deptPageDto.getCurrentPage()==null){
            deptPageDto.setCurrentPage(1);
        }
        if(deptPageDto.getPageSize()==null){
            deptPageDto.setPageSize(10);
        }
        return deptService.queryByPage(deptPageDto);
    }
}
