package com.itheima.security_demo.config;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.security_demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 作用：根据用户名加载用户信息
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 写数据库访问的代码了
        QueryWrapper<com.itheima.security_demo.pojo.User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        com.itheima.security_demo.pojo.User mysqlUser = userMapper.selectOne(queryWrapper);
        Collection<GrantedAuthority> autorities = new ArrayList<>();//
        //查询用户对应的权限信息
        List<String> list = userMapper.selectRoles(username);
        if( list!=null && list.size()>0 ){
            for (String role : list) {
                autorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if(mysqlUser == null){ return null; }
        User user = new User(mysqlUser.getUserName(),mysqlUser.getPassword(),autorities);
        return user;
    }
}
