package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.mapper.DeptMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.service.IDeptService;
import com.itheima.admin.vo.DeptVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {


    @Autowired
    private DeptMapper deptMapper;

    @Override
    public PageVo<DeptVo> queryByPage(PageDto pageDto) {
        IPage<Dept> page = new Page(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<Dept> queryWrapper= new QueryWrapper<>();
        if(pageDto.getQueryString()!=null){
            queryWrapper.like("dept_name",pageDto.getQueryString());
        }
        if(pageDto.getStatus()!=null){
            queryWrapper.like("state",pageDto.getStatus());
        }
        IPage<Dept> deptIPage = deptMapper.selectPage(page, queryWrapper);
        List<DeptVo> collect = getDeptVos(deptIPage.getRecords());
        return new PageVo<DeptVo>(collect, (int) deptIPage.getTotal());
    }

    private List<DeptVo> getDeptVos(List<Dept> deptIPage) {
        List<DeptVo> collect=new ArrayList<>();
        for (Dept dept:deptIPage) {
            DeptVo deptVo = new DeptVo(dept);
            if (dept.getState()==null||dept.getState()==0) {
                deptVo.setState("禁用");
            }else {
                deptVo.setState("启用");
            }
            collect.add(deptVo);
        }
        return collect;
    }

    @Override
    public List<DeptVo> queryAll() {
        List<Dept> depts = deptMapper.selectList(null);
        return getDeptVos(depts);
    }

    @Override
    public Result addDept(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto,dept);
        int insert = deptMapper.insert(dept);
        return new Result(insert>0,insert>0?"添加成功":"添加失败",null);
    }

    @Override
    public Result deleteDept(String id) {
        int i = deptMapper.deleteById(id);
        return new Result(i>0,i>0?"删除成功":"删除失败",null);
    }

    @Override
    public DeptVo queryById(String id) {
        Dept dept = deptMapper.selectById(id);
        DeptVo deptVo = new DeptVo(dept);
        deptVo.setState(dept.getState().toString());
        return deptVo;
    }

    @Override
    public Result updateDept(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto,dept);
        int i = deptMapper.updateById(dept);
        return new Result(i>0,i>0?"修改成功":"修改失败",null);
    }


}
