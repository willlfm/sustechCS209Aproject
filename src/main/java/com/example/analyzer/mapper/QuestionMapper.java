package com.example.analyzer.mapper;

import com.example.analyzer.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//@Mapper
//public interface QuestionMapper extends BaseMapper<question> {}

@Mapper
public interface QuestionMapper {
    void insert(Question question);

    List<String> selectTags();

    List<String> selectTitle();

    List<Question> selectAll();

    int countTopicFrequency(@Param("topicName") String topicName);

    int countErrorFrequency(@Param("errorName") String errorName);
}
