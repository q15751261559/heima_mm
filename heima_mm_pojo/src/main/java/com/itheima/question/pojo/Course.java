package com.itheima.question.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.question.vo.CourseVo;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_course")
public class Course {
    @TableId
    private String id;
    private String name;
    private String state;
    private String remark;
    private Integer orderNo;
    private String createBy;
    private String createDept;
    private String updateBy;
    private Date createTime;
    private Date updateTime;

    public CourseVo toCourseVo(){
        CourseVo course = new CourseVo();
        course.setId(id);
        course.setName(name);
        course.setState(state);
        course.setRemark(remark);
        return course;
    }
}
