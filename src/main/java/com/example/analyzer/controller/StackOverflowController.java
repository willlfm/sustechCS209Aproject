package com.example.analyzer.controller;

import com.example.analyzer.model.dto.ResponseDTO;
import com.example.analyzer.service.StackOverflowDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Validated
@Slf4j
@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {

    private final StackOverflowDataService stackOverflowDataService;

    @Autowired
    public StackOverflowController(StackOverflowDataService stackOverflowDataService) {
        this.stackOverflowDataService = stackOverflowDataService;
    }


    @PutMapping(value = "/update/questions")
    public ResponseDTO<String> updateQuestions() {
        try {
            stackOverflowDataService.updateQuestions();
            return ResponseDTO.success("success");
        } catch (Exception e) {
            return ResponseDTO.fail(e.getMessage());
        }
    }

}
