package com.itheima.question.controller;

import com.itheima.Result;
import com.itheima.common.contants.RedisKeyConstants;
import com.itheima.common.fastdfs.FastDFSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/question")
@Api(value = "文件上传接口", tags = "admin_user", description = "用于实现各种文件上传")
public class FileUploadController {

    @Autowired
    private FastDFSClientUtil clientUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${url.fileServerUrl}")
    private String fileServerUrl;

    @PostMapping("/upload")
    @ApiOperation("上传图片文件")
    public Result upload(MultipartFile file){

        try {
            String fileId = clientUtil.uploadFile(file);
            //写入redis
            redisTemplate.opsForSet().add(RedisKeyConstants.ALL_PIC,fileId);
            return new Result(true,"上传图片成功",fileServerUrl+fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,"上传图片失败",null);
        }
    }
}
