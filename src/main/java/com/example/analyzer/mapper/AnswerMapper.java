package com.example.analyzer.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.analyzer.model.Answer;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
//public interface AnswerMapper extends BaseMapper<answer> {
//}

@Mapper
public interface AnswerMapper {

    void insert(Answer answer);
}
