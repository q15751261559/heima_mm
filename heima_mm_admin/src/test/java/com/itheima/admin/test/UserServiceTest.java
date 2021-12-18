package com.itheima.admin.test;

import com.itheima.admin.AdminApp;
import com.itheima.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AdminApp.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

//    @Test
//    public void testQueryById(){
//        UserVo userVo = userService.queryById("");
//        System.out.println(userVo);
//        Assert.notNull(userVo,"查询对象为空");
//    }
//
//    @Test
//    public void testDeleteById(){
//        boolean result = userService.deleteById("198dd3b0-0d46-4030-8edc-b3fae9703830");
//        Assert.isTrue(result,"删除成功");
//    }
//
//    @Test
//    public void testQueryByPage(){
//        PageDto pageDto = new PageDto();
//        pageDto.setPageSize(10);
//        pageDto.setCurrentPage(1);
//        PageVo<UserPageVo> userPageVoPageVo =
//                userService.queryByPage(pageDto);
//        System.out.println(userPageVoPageVo);
//    }
//    @Test
//    public void testAddUser(){
//        UserDto userDto=new UserDto();
//        userDto.setUserId("157512");
//        userDto.setEmail("332@qq.com");
//        boolean result=userService.addUser(userDto);
//        Assert.isTrue(result,"新增成功");
//    }
}
