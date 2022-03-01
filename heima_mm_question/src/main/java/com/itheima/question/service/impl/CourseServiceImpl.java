package com.itheima.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageDto;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.CourseDto;
import com.itheima.question.mapper.CourseMapper;
import com.itheima.question.pojo.Course;
import com.itheima.question.service.ICourseService;
import com.itheima.question.vo.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageVo<CourseVo> queryByPage(PageDto pageDto) {
        IPage<Course> page = new Page(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<Course> queryWrapper= new QueryWrapper<>();
        if(pageDto.getQueryString()!=null && !pageDto.getQueryString().equals("")){
            queryWrapper.like("name",pageDto.getQueryString());
        }
        if(pageDto.getStatus()!=null && !pageDto.getStatus().equals("")){
            queryWrapper.eq("state",pageDto.getStatus());
        }
        IPage<Course> deptIPage = courseMapper.selectPage(page, queryWrapper);
        List<CourseVo> collect = getCourseVos(deptIPage.getRecords());
        return new PageVo<CourseVo>(collect, (int) deptIPage.getTotal());
    }

    @Override
    public List<CourseVo> queryAll() {
        List<Course> courses = courseMapper.selectList(null);

        List<CourseVo> courseVos = getCourseVos(courses);

        return courseVos;
    }

    private List<CourseVo> getCourseVos(List<Course> courses) {
        List<CourseVo> collect = courses.stream().map(course -> {
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(course, courseVo);
            return courseVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Result addCourse(CourseDto courseDto) {
        int insert = courseMapper.insert(courseDto.toCourse());
        return new Result(insert>0,insert>0?"新增成功":"新增失败",null );
    }

    @Override
    public Result updateCourse(CourseDto courseDto) {
        int insert = courseMapper.updateById(courseDto.toCourse());
        return new Result(insert>0,insert>0?"修改成功":"修改失败",null );
    }

    @Override
    public Result deleteCourse(String id) {
        int insert = courseMapper.deleteById(id);
        return new Result(insert>0,insert>0?"删除成功":"删除失败",null );
    }

    @Override
    public CourseVo queryById(String id) {
        Course course = courseMapper.selectById(id);
        return course.toCourseVo();
    }
}
