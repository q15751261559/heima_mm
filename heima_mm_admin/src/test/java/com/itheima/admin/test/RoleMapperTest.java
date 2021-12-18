package com.itheima.admin.test;

import com.itheima.admin.mapper.RoleMapper;
import com.itheima.admin.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testSelect(){
        System.out.println(("----- selectAll method test ------"));
        List<Role> roleList= roleMapper.selectList(null);
        System.out.println(roleList);
    }
}
