package com.example.analyzer.controller;

import com.example.analyzer.model.dto.ResponseDTO;
import com.example.analyzer.service.StackOverflowDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {

    private final StackOverflowDataService stackOverflowDataService;

    @PutMapping(value = "/update/questions")
    public ResponseDTO<String> updateQuestions(@PathVariable String deptId) {
        try {
            stackOverflowDataService.updateQuestions();
            return ResponseDTO.success("success");
        } catch (Exception e) {
            return ResponseDTO.failure(e.getMessage());
        }
    }

}
