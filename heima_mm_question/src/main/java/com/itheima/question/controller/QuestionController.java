package com.itheima.question.controller;

import com.itheima.PageVo;
import com.itheima.Result;
import com.itheima.question.dto.QuestionDto;
import com.itheima.question.dto.QuestionPageDto;
import com.itheima.question.pojo.Question;
import com.itheima.question.pojo.QuestionItem;
import com.itheima.question.service.impl.QuestionServiceImpl;
import com.itheima.question.vo.QuestionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Api(value = "答题模块-题目接口", tags = "admin_user", description = "用于实现后台题目的增删改查操作")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;


    @PostMapping("/list")
    @ApiOperation("分页查询学科")
    public PageVo<QuestionVo> queryByPage(@RequestBody QuestionPageDto pageDto){
        return questionService.queryByPage(pageDto);
    }

    @PostMapping("/add")
    @ApiOperation("添加题目")
    public Result addQuestion(@RequestBody QuestionDto questionDto){
        boolean result  = questionService.addQuestion(questionDto);
        return new Result(result,result?"添加试题成功":"添加试题失败",null);
    }

    @PostMapping("/item/add")
    @ApiOperation("添加题目选项")
    public Result addQuestionItem(@RequestBody QuestionItem questionItem){
        boolean result  = questionService.addQuestionItem(questionItem);
        return new Result(result,result?"添加试题选项成功":"添加试题选项失败",null);
    }


    @PutMapping("/edit")
    @ApiOperation("编辑题目")
    public Result editQuestion(@RequestBody QuestionDto questionDto){
        boolean result  = questionService.editQuestion(questionDto);
        return new Result(result,result?"编辑试题成功":"编辑试题失败",null);
    }

    @GetMapping("/{id}")
    public Question queryById(@PathVariable("id") String id){
        return questionService.queryById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return questionService.deleteById(id);
    }

    @GetMapping("/item/{id}")
    public List<QuestionItem> queryItemByQuestionId(@PathVariable("id") String id){
        return questionService.queryItemById(id);
    }



    @DeleteMapping("/item/{id}")
    public Result deleteItemById(@PathVariable("id") String id){
        return questionService.deleteItemById(id);
    }


}
