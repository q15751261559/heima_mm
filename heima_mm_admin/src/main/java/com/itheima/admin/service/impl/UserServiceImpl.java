package com.itheima.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.PageVo;
import com.itheima.admin.dto.RoleModuleDto;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.mapper.UserMapper;
import com.itheima.admin.mapper.UserRoleMapper;
import com.itheima.admin.pojo.User;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserVo queryById(String id) {
        User user = userMapper.selectById(id);
        UserVo userVo = new UserVo();
        if(user!=null){
            BeanUtils.copyProperties(user,userVo);
        }
        return userVo;
    }

    @Override
    public boolean deleteById(String id) {
        userRoleMapper.deleteByUserId(id);
        return userMapper.deleteById(id);
    }

    @Override
    public PageVo<UserPageVo> queryByPage(UserPageDto userPageDto) {
        //开启分页
        PageHelper.startPage(userPageDto.getCurrentPage(), userPageDto.getPageSize());


        List<User> pages =  userMapper.selectByPage(userPageDto.getStatus(), userPageDto.getUsername());
        PageInfo<User> page = new PageInfo<>(pages);

        //List<User>  ==>  List<UserPageVo>
        List<UserPageVo> list = page.getList().stream().map(user -> {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user,userPageVo);
            userPageVo.setStatus(user.getState());
            return userPageVo;
        }).collect(Collectors.toList());


 /*       List<UserPageVo> list1 = new ArrayList<>();
        for (User user : page.getList()) {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user,userPageVo);
            userPageVo.setStatus(user.getState());
            list1.add(userPageVo);
        }*/

        return new PageVo<UserPageVo>(list,Long.valueOf(page.getTotal()).intValue());
    }

    @Override
    public boolean addUser(UserDto userDto) {
        User user=new User();
        if (userDto!=null)
        {
            BeanUtils.copyProperties(userDto,user);
        }
        return userMapper.addUser(user);
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        User user=new User();
        if (userDto!=null)
        {
            BeanUtils.copyProperties(userDto,user);
        }
        return userMapper.updateUser(user);
    }

    @Override
    public boolean roleUser(UserRoleDto userRoleDto) {
        List<String> roleIds = userRoleDto.getRoles();
        boolean flag=false;
        for (String s:roleIds){
            flag=userMapper.roleUser(userRoleDto.getUserId(),s);
        }
        return flag;
    }

}
