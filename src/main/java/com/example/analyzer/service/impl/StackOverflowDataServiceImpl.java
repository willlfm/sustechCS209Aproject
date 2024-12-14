package com.example.analyzer.service.impl;

import com.example.analyzer.model.dto.TopicDTO;
import com.example.analyzer.service.QuestionService;
import com.example.analyzer.service.StackOverflowDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * stackoverflow data service
 */
@Service
@RequiredArgsConstructor
public class StackOverflowDataServiceImpl implements StackOverflowDataService {

    private final QuestionService questionService;

    @Override
    public void updateQuestions() {

    }

    @Override
    public List<TopicDTO> getTopNTopics(int n) {
        return null;
    }

    @Override
    public List<TopicDTO> getTopNUserEngageTopics(int n) {
        return null;
    }
}
