package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RestController;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
}
