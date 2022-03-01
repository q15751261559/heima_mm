package com.itheima.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itheima.question.mapper")
public class QuestionApp {
    public static void main(String[] args) {
        SpringApplication.run(QuestionApp.class, args);
    }
}
