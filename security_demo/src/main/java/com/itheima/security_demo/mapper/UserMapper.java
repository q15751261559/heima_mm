package com.itheima.security_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.security_demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
