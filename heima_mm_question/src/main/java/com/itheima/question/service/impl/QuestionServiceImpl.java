package com.itheima.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.common.contants.RedisKeyConstants;
import com.itheima.question.dto.QuestionDto;
import com.itheima.question.dto.QuestionPageDto;
import com.itheima.question.mapper.QuestionItemMapper;
import com.itheima.question.mapper.QuestionMapper;
import com.itheima.question.pojo.Question;
import com.itheima.question.pojo.QuestionItem;
import com.itheima.question.service.IQuestionService;
import com.itheima.question.vo.QuestionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionItemMapper questionItemMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${url.fileServerUrl}")
    private String fileServerUrl;

    @Override
    public boolean addQuestion(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        int insert = questionMapper.insert(question);

        //将问题id存入redis的set集合中，用于动态审核
        String id = question.getId();
        redisTemplate.opsForSet().add(RedisKeyConstants.AUDIT_QUESTION,id);

        //保存图片id到redis中，清理垃圾图片使用的
        String pid = question.getPicture();
        redisTemplate.opsForSet().add(RedisKeyConstants.USE_PIC,pid.replace(fileServerUrl,""));
        return insert>0;
    }

    public Question queryById(String id) {
        return questionMapper.selectById(id);
    }

    public List<QuestionItem> queryItemById(String id) {
        QueryWrapper<QuestionItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id",id);
        List<QuestionItem> questionItems = questionItemMapper.selectList(queryWrapper);
        return questionItems;
    }


    public boolean editQuestion(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        String pid = question.getPicture();
        redisTemplate.opsForSet().add(RedisKeyConstants.USE_PIC,
                pid.replace(fileServerUrl,""));
        int i = questionMapper.updateById(question);
        return i>0;
    }

    public PageVo<QuestionVo> queryByPage(QuestionPageDto pageDto) {
        IPage<Question> page = new Page(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<Question> queryWrapper= new QueryWrapper<>();
        if(StringUtils.isNotEmpty(pageDto.getQueryString())){
            queryWrapper.like("subject",pageDto.getQueryString());
        }
        if(StringUtils.isNotEmpty(pageDto.getCatalogId())){
            queryWrapper.eq("catalog_id",pageDto.getCatalogId());
        }

        if(StringUtils.isNotEmpty(pageDto.getCompanyId())){
            queryWrapper.eq("company_id",pageDto.getCompanyId());
        }

        if(StringUtils.isNotEmpty(pageDto.getCourseId())){
            queryWrapper.eq("course_id",pageDto.getCourseId());
        }
        queryWrapper.orderByDesc("create_time");
        IPage<Question> deptIPage = questionMapper.selectPage(page, queryWrapper);
        List<QuestionVo> collect = getquestionVos(deptIPage.getRecords());
        return new PageVo<QuestionVo>(collect, (int) deptIPage.getTotal());
    }

    private List<QuestionVo> getquestionVos(List<Question> records) {
        List<QuestionVo> collect = records.stream().map(question -> {
            return question.toQuestionVo();
        }).collect(Collectors.toList());
        return collect;
    }

    public Result deleteById(String id) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Question::getId,id);
        int delete = questionMapper.delete(wrapper);
        return new Result(delete>0,delete>0?"删除成功":"删除失败",null);
    }

    public boolean addQuestionItem(QuestionItem questionItem) {
        int insert = questionItemMapper.insert(questionItem);
        return insert>0;
    }

    public Result deleteItemById(String id) {
        int delete = questionItemMapper.deleteById(id);
        return new Result(delete>0,delete>0?"删除成功":"删除失败",null);
    }
}
