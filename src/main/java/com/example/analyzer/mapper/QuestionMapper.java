package com.example.analyzer.mapper;

import com.example.analyzer.model.Question;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
//public interface QuestionMapper extends BaseMapper<question> {}

@Mapper
public interface QuestionMapper {
    void insert(Question question);
}
