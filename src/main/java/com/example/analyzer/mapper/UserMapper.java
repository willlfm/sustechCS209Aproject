package com.example.analyzer.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.analyzer.model.Person;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
//public interface UserMapper extends BaseMapper<person> {
//}

@Mapper
public interface UserMapper {

    void insert(Person person);
}
