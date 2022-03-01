package com.itheima.admin.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.vo.DeptVo;

import java.util.List;

public interface IDeptService {
    PageVo<DeptVo> queryByPage(PageDto pageDto);


    List<DeptVo> queryAll();

    Result addDept(DeptDto deptDto);

    Result deleteDept(String id);

    DeptVo queryById(String id);

    Result updateDept(DeptDto deptDto);
}
