package com.itheima.question.dto;


import com.itheima.question.pojo.Course;
import lombok.Data;

@Data
public class CourseDto {
    private String id;
    private String name;
    private String state;
    private String remark;

    public Course toCourse(){
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setState(state);
        course.setRemark(remark);
        return course;
    }
}
