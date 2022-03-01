package com.itheima.question.dto;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/21
 * @Description:
 */
@Data
public class QuestionEditDto {
    private String companyId;
    private String id;
    private String catalogId;
    private String subject;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String analysis;
    private String picture;
}
