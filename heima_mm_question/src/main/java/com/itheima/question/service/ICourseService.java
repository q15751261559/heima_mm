package com.itheima.question.service;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CourseDto;
import com.itheima.question.vo.CourseVo;

import java.util.List;

public interface ICourseService {
    PageVo<CourseVo> queryByPage(PageDto pageDto);

    List<CourseVo> queryAll();


    Result addCourse(CourseDto courseDto);

    Result updateCourse(CourseDto courseDto);

    Result deleteCourse(String id);

    CourseVo queryById(String id);

}
