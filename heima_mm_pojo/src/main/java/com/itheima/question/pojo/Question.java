package com.itheima.question.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.itheima.question.vo.QuestionVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@TableName("st_question")
public class Question {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String catalogId;
    private String catalogName;
    private String courseId;
    private String courseName;
    private String number;
    private String subject;
    private String type;
    private String difficulty;
    private String analysis;
    private String analysisVideo;
    private String remark;
    private String isClassic;
    private String state;
    private String reviewStatus;
    private String createBy;
    private String createDept;
    private String updateBy;
    private String companyId;
    private String companyName;
    private String picture;
    @TableField(fill = FieldFill.INSERT)
    private Date   createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date   updateTime;

    public QuestionVo toQuestionVo(){
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(this,questionVo);
        //题目类型： 1. 单选 2. 多选 5. 简答
        this.type = this.type==null?"":this.type;
        switch (this.type){
            case "1" : questionVo.setType("单选");break;
            case "2" : questionVo.setType("多选");break;
            case "5" : questionVo.setType("简答");break;
            default: questionVo.setType("其他");
        }

        this.difficulty = this.difficulty==null?"":this.difficulty;
        //难度： 1 简单  2 一般  3 困难
        switch (this.difficulty){
            case "1" : questionVo.setDifficulty("*");break;
            case "2" : questionVo.setDifficulty("***");break;
            case "3" : questionVo.setDifficulty("******");break;
            default: questionVo.setDifficulty("未知");
        }

        this.state = this.state==null?"":this.state;
        //题目状态  0 待发布（待审核、已拒绝）  1 已发布（已审核）  2 已下架（已审核）
        switch (this.state){
            case "0" : questionVo.setState("待发布（待审核、已拒绝）");break;
            case "1" : questionVo.setState("已发布（已审核）");break;
            case "2" : questionVo.setState("已下架（已审核）");break;
            default: questionVo.setState("位置");
        }
        return questionVo;
    }
}
