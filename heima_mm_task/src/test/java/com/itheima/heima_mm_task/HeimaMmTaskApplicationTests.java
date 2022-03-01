package com.itheima.heima_mm_task;

import com.itheima.common.contants.RedisKeyConstants;
import com.itheima.common.fastdfs.FastDFSClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Set;

@SpringBootTest
class HeimaMmTaskApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Test
    public void test1(){
        Set<String> pics = redisTemplate.opsForSet()
                .difference(RedisKeyConstants.ALL_PIC, RedisKeyConstants.USE_PIC);
        System.out.println("定时任务开始执行，需要清理的图片有："+pics);
        for (String pic : pics) {
            try {
                fastDFSClientUtil.delFile(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("hello spring task"+new Date());
    }


}
