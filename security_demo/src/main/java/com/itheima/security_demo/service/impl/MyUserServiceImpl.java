package com.itheima.security_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.security_demo.mapper.UserMapper;
import com.itheima.security_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MyUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        User mysqlUser = userMapper.selectOne(queryWrapper);
        System.out.println(mysqlUser);
        Collection<GrantedAuthority> autorities = new ArrayList<>();//
        autorities.add(new SimpleGrantedAuthority("ADMIN"));
        if(mysqlUser == null){ return null; }
        User user = new User(mysqlUser.getUsername(),mysqlUser.getPassword(),autorities);
        System.out.println(user);
        return user;
    }
}
