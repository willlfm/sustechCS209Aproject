package com.example.mvcdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StackoverflowAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowAnalyzerApplication.class, args);
    }
}

@RestController
class DataController {

    // 假设我们有一个用于存储数据的数据库服务
    private final DatabaseService databaseService;

    public DataController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // 这个端点用于接收数据并存储到数据库
    @GetMapping("/storeData")
    public String storeData(@RequestParam String data) {
        databaseService.saveData(data);
        return "Data received and stored successfully!";
    }
}

// 假设的数据库服务类，你需要根据你的数据库实现具体的存储逻辑
class DatabaseService {
    public void saveData(String data) {
        // 实现数据存储逻辑，例如使用JDBC或JPA存储到数据库
        System.out.println("Data stored: " + data);
    }
}