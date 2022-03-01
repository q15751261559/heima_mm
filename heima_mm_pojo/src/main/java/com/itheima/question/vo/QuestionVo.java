package com.itheima.question.vo;

import lombok.Data;

/**
 * @Author Jason
 * @Date 2021/12/21
 * @Description:
 */
@Data
public class QuestionVo {
    private String companyName;
    private String id;
    private String catalogName;
    private String subject;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String reviewStatus;
}
