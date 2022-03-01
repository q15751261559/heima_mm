package com.itheima.question.dto;

import com.itheima.PageDto;
import lombok.Data;

@Data
public class QuestionPageDto extends PageDto {
    private String companyId;
    private String catalogId;
    private String courseId;
}
