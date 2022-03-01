package com.itheima.question.doc;

import com.itheima.question.pojo.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDoc {
    private String companyName;
    private String id;
    private String catalogName;
    private String subject;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String reviewStatus;

    public QuestionDoc(Question question)
    {
        this.companyName=question.getCompanyName();
        this.id=question.getId();
        this.catalogName=question.getCatalogName();
        this.subject=question.getSubject();
        this.type=question.getType();
        this.difficulty=question.getDifficulty();
        this.isClassic=question.getIsClassic();
        this.state=question.getState();
        this.reviewStatus=question.getReviewStatus();
    }
}
