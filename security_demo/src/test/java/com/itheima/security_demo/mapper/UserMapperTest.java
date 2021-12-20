package com.itheima.security_demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.security_demo.SecurityDemoApplication;
import com.itheima.security_demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SecurityDemoApplication.class)
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void test1(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name","老王");
        User mysqlUser = userMapper.selectOne(queryWrapper);
        System.out.println(mysqlUser);
    }
}
