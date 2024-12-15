package com.example.analyzer;

import com.example.analyzer.service.StackOverflowDataService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.analyzer.*"})
@MapperScan(basePackages = {"com.example.analyzer.mapper"})
public class StackoverflowAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowAnalyzerApplication.class, args);
        System.out.println("启动成功");
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(StackOverflowDataService service){
//        return args -> {
//            service.updateQuestions();
//        };
//    }
}