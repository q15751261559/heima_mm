package com.itheima.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.admin.dto.UserDto;
import com.itheima.admin.mapper.UserMapper;
import com.itheima.admin.mapper.UserRoleMapper;
import com.itheima.admin.pojo.User;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            userVo.setUsername(user.getUserName());
        }
        return userVo;
    }

    @Override
    public boolean deleteById(String id) {
        //删除用户和角色的关联关系
        userRoleMapper.deleteByUserId(id);
        //删除用户
        return userMapper.deleteById(id);
    }

    @Override
    public PageVo<UserPageVo> queryByPage(PageDto pageDto) {
        //设置分页参数
        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getPageSize());
        List<User> list = userMapper.selectByPage(pageDto.getStatus(),pageDto.getQueryString());
        PageInfo<User> pageInfo = new PageInfo<>(list);
        //List<User>  ==> List<UserPageVo>
        List<UserPageVo> collect = pageInfo.getList().stream().map(user -> {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user, userPageVo);
            userPageVo.setUsername(user.getUserName());
            userPageVo.setStatus(user.getState());
            return userPageVo;
        }).collect(Collectors.toList());
        return new PageVo<>(collect,Long.valueOf(pageInfo.getTotal()).intValue());
    }

    @Override
    public Result addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setUserName(userDto.getUsername());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int insert = userMapper.insert(user);
        return new Result(insert>0,insert>0?"添加用户成功":"添加用户失败",null );
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setUpdateTime(new Date());
        user.setUserName(userDto.getUsername());
        int i = userMapper.updateById(user);
        return i>0;
    }

    @Override
    public int userToRole(String userId,
                          List<Object> roleIds) {
        return userMapper.addUserToRole(userId,roleIds);
    }

    @Override
    public Result queryRoleByUserId(String id) {
        List<String> roleIds = userMapper.selectRoleIdsByUserId(id);
        if( roleIds!=null && roleIds.size()>0 ){
            return new Result(true,"查询成功",roleIds);
        }else {
            return new Result(false,"查询失败",null);
        }
    }
}
