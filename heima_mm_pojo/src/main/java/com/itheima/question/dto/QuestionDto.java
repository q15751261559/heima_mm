package com.itheima.question.dto;

import lombok.Data;

@Data
public class QuestionDto {
    	private String companyId;
        private String catalogId;
        private String catalogName;
        private String subject;
        private String type;
        private String difficulty;
        private String isClassic;
        private String state;
        private String analysis;
        private String picture;
}
