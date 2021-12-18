package com.itheima.admin.service;

import com.itheima.PageVo;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.vo.DeptPageVo;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.UserPageVo;

import java.util.List;

public interface DeptService {
    public List<DeptVo> queryAllDept();
    public DeptVo queryById(String id);
    boolean deleteById(String id);
    boolean addDept(DeptDto deptDto);
    PageVo<DeptPageVo> queryByPage(DeptPageDto deptPageDto);
    boolean updateDept(DeptDto deptDto);
}
