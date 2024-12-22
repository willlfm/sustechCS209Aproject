package com.example.analyzer.controller;

import com.example.analyzer.model.dto.ResponseDTO;
import com.example.analyzer.model.dto.TopicDTO;
import com.example.analyzer.service.StackOverflowDataService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Validated
@Slf4j
@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {

    @Resource
    private StackOverflowDataService stackOverflowDataService;

//    @Autowired
//    public StackOverflowController(StackOverflowDataService stackOverflowDataService) {
//        this.stackOverflowDataService = stackOverflowDataService;
//    }


    @PutMapping(value = "/update/questions")
    public ResponseDTO<String> updateQuestions() {
        try {
            stackOverflowDataService.updateQuestions();
            return ResponseDTO.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @GetMapping(value = "/get/topNTopics")
    public ResponseDTO<List<TopicDTO>> getTopNTopics() {
        try {
            List<TopicDTO> topNTopics = stackOverflowDataService.getTopNTopics(10);
            return ResponseDTO.success(topNTopics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.fail(e.getMessage());
        }
    }

}
