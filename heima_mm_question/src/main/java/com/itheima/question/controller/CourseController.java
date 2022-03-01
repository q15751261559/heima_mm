package com.itheima.question.controller;

import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CourseDto;
import com.itheima.question.service.ICourseService;
import com.itheima.question.vo.CourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")
@Api(value = "管理后台学科模块接口", tags = "admin_user", description = "用于实现后台学科的增删改查操作")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @PostMapping("/list")
    @ApiOperation("分页查询学科")
    public PageVo<CourseVo> queryByPage(@RequestBody PageDto pageDto){
        return courseService.queryByPage(pageDto);
    }

    @GetMapping("/listall")
    @ApiOperation("查询所有学科")
    public List<CourseVo> queryAll(){
        return courseService.queryAll();
    }

    @PostMapping("/add")
    @ApiOperation("添加学科")
    public Result addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑学科")
    public Result updateCourse(@RequestBody CourseDto courseDto){
        return courseService.updateCourse(courseDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除学科")
    public Result deleteCourse(@PathVariable("id") String id){
        return courseService.deleteCourse(id);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询学科")
    public CourseVo queryById(@PathVariable("id") String id){
        return courseService.queryById(id);
    }


}
