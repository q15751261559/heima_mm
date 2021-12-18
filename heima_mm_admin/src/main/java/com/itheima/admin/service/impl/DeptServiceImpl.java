package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.PageVo;
import com.itheima.admin.dto.DeptDto;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.mapper.DeptMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.pojo.User;
import com.itheima.admin.service.DeptService;
import com.itheima.admin.vo.DeptPageVo;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    @Override
    public List<DeptVo> queryAllDept() {
        List<Dept> depts=deptMapper.selectList(null);
        List<DeptVo> deptVos=new ArrayList<>();
        if(depts!=null){
            for (Dept dept:depts)
            {
                DeptVo deptVo=new DeptVo();
                BeanUtils.copyProperties(dept,deptVo);
                deptVos.add(deptVo);
            }
        }
        return deptVos;
    }

    @Override
    public DeptVo queryById(String id) {
        Dept dept = deptMapper.selectById(id);
        DeptVo deptVo=new DeptVo();
        if(dept!=null){
            BeanUtils.copyProperties(dept,deptVo);
        }
        return deptVo;
    }

    @Override
    public boolean deleteById(String id) {
        Dept dept=new Dept();
        dept.setDeptId(id);
        int i=deptMapper.deleteById(dept);
        return i != 0;
    }

    @Override
    public boolean addDept(DeptDto deptDto) {
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        deptDto.setDeptId(uuid);
        Dept dept=new Dept();
        BeanUtils.copyProperties(deptDto,dept);
        boolean flag=false;
        int i=deptMapper.insert(dept);
        if (i!=0)
        {
            flag=true;
        }
        return flag;
    }

    @Override
    public PageVo<DeptPageVo> queryByPage(DeptPageDto deptPageDto) {
        //开启分页
        PageHelper.startPage(deptPageDto.getCurrentPage(), deptPageDto.getPageSize());
        QueryWrapper<Dept> wrapper=new QueryWrapper<>();
        if (deptPageDto.getDeptName()!=null)
        {
            wrapper.like("dept_name",deptPageDto.getDeptName());
        }
        if (deptPageDto.getStatus()!=null){
            wrapper.eq("state",deptPageDto.getStatus());

        }
        List<Dept> pages = deptMapper.selectList(wrapper);
        PageInfo<Dept> page = new PageInfo<>(pages);

        //List<User>  ==>  List<UserPageVo>
        List<DeptPageVo> list = page.getList().stream().map(dept -> {
            DeptPageVo deptPageVo = new DeptPageVo();
            BeanUtils.copyProperties(dept,deptPageVo);
            deptPageVo.setState(dept.getState());
            return deptPageVo;
        }).collect(Collectors.toList());


 /*       List<UserPageVo> list1 = new ArrayList<>();
        for (User user : page.getList()) {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user,userPageVo);
            userPageVo.setStatus(user.getState());
            list1.add(userPageVo);
        }*/

        return new PageVo<DeptPageVo>(list,Long.valueOf(page.getTotal()).intValue());
    }

    @Override
    public boolean updateDept(DeptDto deptDto) {
        Dept dept = new Dept();
        if(deptDto!=null){
            BeanUtils.copyProperties(deptDto,dept);
        }
        return deptMapper.updateById(dept)!=0;
    }
}
