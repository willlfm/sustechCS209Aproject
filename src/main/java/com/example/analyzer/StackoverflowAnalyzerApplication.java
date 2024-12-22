package com.example.analyzer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.analyzer.*"})
@MapperScan(basePackages = {"com.example.analyzer.mapper"})
public class StackoverflowAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowAnalyzerApplication.class, args);
        System.out.println("启动成功");
    }
}