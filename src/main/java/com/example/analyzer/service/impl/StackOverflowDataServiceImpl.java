package com.example.analyzer.service.impl;

import com.example.analyzer.model.Question;
import com.example.analyzer.model.dto.TopicDTO;
import com.example.analyzer.service.QuestionService;
import com.example.analyzer.service.StackOverflowDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * stackoverflow data service
 */
@Service
@RequiredArgsConstructor
public class StackOverflowDataServiceImpl implements StackOverflowDataService {

    @Autowired
    private final QuestionService questionService;

//    private final RestTemplate restTemplate;

    @Autowired
    private final WebClient webClient;

    @Override
    public void updateQuestions() {
        String baseUrl = "https://api.stackexchange.com/2.3/questions?tagged=java&pagesize=100&order=desc&sort=votes&site=stackoverflow&filter=!22Zfkj9b1*14uZ*HGj7Z*";

        for (int page = 1; page <= 10; page++) {
            System.out.println("page: " + page);
            try {
                String url = baseUrl + "&page=" + page;
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");

                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MyClient/1.0)");

                int responseCode = con.getResponseCode();
                System.out.println("Response Code : " + responseCode);

                // 读取响应
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印结果
                String responseString = response.toString();
                System.out.println(responseString);

                // 使用Jackson将JSON字符串转换为JsonNode
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);

                JsonNode itemsNode = rootNode.path("items");
                List<Question> questions = new ArrayList<>();
                for (JsonNode itemNode : itemsNode) {
                    JsonNode tagsNode = itemNode.path("tags");
                    List<String> tagsList = new ArrayList<>();
                    tagsNode.forEach(tag -> tagsList.add(tag.asText()));
                    String tags = String.join(",", tagsList);
                    int id = itemNode.path("id").asInt();
                    String title = itemNode.path("title").asText();
                    System.out.println("title: " + title);
                    String body = itemNode.path("body").asText();
                    System.out.println("body: " + body);

                    Question question = new Question();
                    question.setTags(tags);
                    question.setQuestionId(id);
                    questions.add(question);
                }
                // questionService.saveBatch(questions);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

//    public void updateQuestions() {
//        String url = "https://api.stackexchange.com/2.3/questions?tagged=java&pagesize=100&order=desc&sort=votes&site=stackoverflow&filter=!22Zfkj9b1*14uZ*HGj7Z*";
//
//        // 发送GET请求并接收响应
//        Mono<String> responseMono = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class);
//
//        responseMono.subscribe(response -> {
//            // 使用Jackson将JSON字符串转换为JsonNode
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode rootNode = objectMapper.readTree(response);
//
//                JsonNode itemsNode = rootNode.path("items");
//
//                List<Question> questions = new ArrayList<>();
//                for (JsonNode itemNode : itemsNode) {
//                    JsonNode tagsNode = itemNode.path("tags");
//                    List<String> tagsList = new ArrayList<>();
//                    tagsNode.forEach(tag -> tagsList.add(tag.asText()));
//                    String tags = String.join(",", tagsList);
//                    int id = itemNode.path("id").asInt();
//                    String title = itemNode.path("title").asText();
//                    System.out.println("title: " + title);
//                    String body = itemNode.path("body").asText();
//                    System.out.println("body: " + body);
//
//                    Question question = new Question();
//                    question.setTags(tags);
//                    question.setQuestionId(id);
//                    questions.add(question);
//                }
////                questionService.saveBatch(questions);
//            } catch (IOException e) {
//                e.printStackTrace();
//                // 处理异常
//            }
//        });
//    }

    @Override
    public List<TopicDTO> getTopNTopics(int n) {
        return null;
    }

    @Override
    public List<TopicDTO> getTopNUserEngageTopics(int n) {
        return null;
    }
}
