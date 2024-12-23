package com.example.analyzer.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.analyzer.model.Answer;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Map;

//@Mapper
//public interface AnswerMapper extends BaseMapper<answer> {
//}

@Mapper
public interface AnswerMapper {

    void insert(Answer answer);

    @MapKey("quality")
    Map<String, Map<String, Double>> calculateAvgCreationDateDiff();

    @MapKey("quality")
    Map<String, Map<String, BigDecimal>> calculateAvgReputation();

    @MapKey("quality")
    Map<String, Map<String, BigDecimal>> calculateAvgAcceptRate();
}
