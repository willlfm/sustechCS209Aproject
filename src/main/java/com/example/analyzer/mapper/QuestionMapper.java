package com.example.analyzer.mapper;

import com.example.analyzer.model.Question;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
//public interface QuestionMapper extends BaseMapper<question> {}

@Mapper
public interface QuestionMapper {
    void insert(Question question);

    List<String> selectTags();
}
