package com.example.analyzer.service;

import com.example.analyzer.model.dto.TopicDTO;

import java.util.List;

public interface StackOverflowDataService {

    /**
     * 调用stack exchange API, 更新数据库中的问题与回答
     */
    void updateQuestions();

    /**
     * 获取被问到次数最多的java话题
     */
    List<TopicDTO> getTopNTopics(int n);

    /**
     * 获取高信用用户参与最多的java话题
     */
    List<TopicDTO> getTopNUserEngageTopics(int n);

}
