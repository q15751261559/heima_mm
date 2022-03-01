package com.itheima.heima_mm_task.task;

import com.itheima.common.contants.RedisKeyConstants;
import com.itheima.common.fastdfs.FastDFSClientUtil;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
@Slf4j
public class ClearPicTask {

    @Autowired
    private FastDFSClientUtil fastDFSClientUtil;

    @Autowired
    private RedisTemplate redisTemplate;
    //  秒       分      小时    日    月     周
    //  0-59    0-59    0-23   1-31  1-12  1-7
    /*
     特殊字符
        * : 任意
        ? : 弃用 【只能作用在日和周这两个域，这两个域一定是有一个不用的】
        - : 0-5  0 1 2 3 4 5
        , : 0,8,23,54
        / : 0/5  0 5  10  15  20  25  30  35  40  45  50  55
     */
    // 每天的凌晨0时0分0秒执行  0  0  0  *   *  ?


    @XxlJob("clearPic")
    public void clearPic() {
        Set<String> pics = redisTemplate.opsForSet()
                .difference(RedisKeyConstants.ALL_PIC, RedisKeyConstants.USE_PIC);
        log.info("定时任务开始执行，需要清理的图片有："+pics);
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
