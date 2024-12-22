package com.example.analyzer.service.impl;

import com.example.analyzer.mapper.AnswerMapper;
import com.example.analyzer.mapper.QuestionMapper;
import com.example.analyzer.mapper.UserMapper;
import com.example.analyzer.model.Answer;
import com.example.analyzer.model.Person;
import com.example.analyzer.model.Question;
import com.example.analyzer.model.dto.TopicDTO;
import com.example.analyzer.service.StackOverflowDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.sql.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * stackoverflow data service
 */
@Service
@RequiredArgsConstructor
public class StackOverflowDataServiceImpl implements StackOverflowDataService {

//    @Autowired
//    private final QuestionService questionService;

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private AnswerMapper answerMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
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
                if (responseCode==200){
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    String responseString = response.toString();
//                System.out.println(responseString);

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(responseString);

                    JsonNode itemsNode = rootNode.path("items");
                    List<Question> questions = new ArrayList<>();
                    for (JsonNode itemNode : itemsNode) {
                        int questionId = itemNode.path("question_id").asInt();
                        String title = itemNode.path("title").asText();
//                    int askerId = itemNode.path("asker_id").asInt();
                        JsonNode tagsNode = itemNode.path("tags");
                        List<String> tagsList = new ArrayList<>();
                        tagsNode.forEach(tag -> tagsList.add(tag.asText()));
                        String tags = String.join(",", tagsList);
                        String body = itemNode.path("body").asText();
                        int viewCount = itemNode.path("view_count").asInt();
                        int commentCount = itemNode.path("comment_count").asInt();
                        int downVoteCount = itemNode.path("down_vote_count").asInt();
                        int upVoteCount = itemNode.path("up_vote_count").asInt();
                        int favoriteCount = itemNode.path("favorite_count").asInt();
                        int score = itemNode.path("score").asInt();
                        int answerCount = itemNode.path("answer_count").asInt();
                        String link = itemNode.path("link").asText();

                        int timestamp = itemNode.path("creation_date").asInt();
                        long milliseconds = timestamp * 1000L;
                        Date creationDate = new Date(milliseconds);
//                    System.out.println("Date from timestamp: " + date.toString());

                        JsonNode answersNode = itemNode.path("answers");
//                        List<Answer> answersList = new ArrayList<>();

                        for (JsonNode answerNode : answersNode) {
                            Answer answer = new Answer();
                            Person owner = new Person();
                            JsonNode ownerNode = itemNode.path("owner");
                            int ownerUserId = ownerNode.path("user_id").asInt();
                            int ownerAccountId = ownerNode.path("account_id").asInt();
                            String name = ownerNode.path("display_name").asText();
                            int reputation = ownerNode.path("reputation").asInt();
                            int acceptRate = ownerNode.path("accept_rate").asInt();

                            owner.setUserId(ownerUserId);
                            owner.setDisplayName(name);
                            owner.setReputation(reputation);
                            owner.setAcceptRate(acceptRate);
                            owner.setAccountId(ownerAccountId);

                            userMapper.insert(owner);

                            answer.setOwnerUserId(ownerUserId);

                            int answerId = answerNode.path("answer_id").asInt();
                            int quesId = answerNode.path("question_id").asInt();
                            int answerScore = answerNode.path("score").asInt();
                            boolean isAccepted = answerNode.path("is_accepted").asBoolean();
                            int answerTimestamp = itemNode.path("creation_date").asInt();
                            long answerMilliseconds = answerTimestamp * 1000L;
                            Date answerCreationDate = new Date(answerMilliseconds);

                            answer.setAnswerId(answerId);
                            answer.setQuestionId(quesId);
                            answer.setScore(answerScore);
                            answer.setCreationDate(answerCreationDate);
                            answer.setAccepted(isAccepted);
                            answerMapper.insert(answer);
                            System.out.println("answer saved: " + answerId);
//                            answersList.add(answer);
                        }

                        Question question = new Question();
                        question.setQuestionId(questionId);
                        question.setTitle(title);
                        question.setTags(tags);
                        question.setBody(body);
                        question.setViewCount(viewCount);
                        question.setCommentCount(commentCount);
                        question.setDownVoteCount(downVoteCount);
                        question.setUpVoteCount(upVoteCount);
                        question.setFavoriteCount(favoriteCount);
                        question.setScore(score);
                        question.setAnswerCount(answerCount);
                        question.setLink(link);
                        question.setCreationDate(creationDate);

//                        questions.add(question);
//                        System.out.println(question.getTitle());
//                        System.out.println(question.getBody());
//                        System.out.println(question.getTags());
//                        System.out.println(question.getLink());
                        questionMapper.insert(question);
                        System.out.println("question saved: " + questionId);
                    }
                    System.out.println("page saved!");
//                    questionService.saveBatch(questions);
                }

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<TopicDTO> getTopNTopics(int n) {
        List<String> tagsList = questionMapper.selectTags();
        Map<String, Long> topicFrequency = tagsList.stream()
                .flatMap(tag -> Arrays.stream(tag.split(",\\s*")))
                .filter(tag -> !"java".equals(tag.trim()) && !tag.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 将map转换成list，并按频率降序排序
        List<Map.Entry<String, Long>> entries = new ArrayList<>(topicFrequency.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 转换成TopicDTO列表，并取前N个
        List<TopicDTO> topNTopics = entries.stream()
                .limit(n)
                .map(entry -> new TopicDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return topNTopics;
    }

    @Override
    public int getTopicFrequency(String topic) {
        return questionMapper.countTopicFrequency(topic);
    }

    @Override
    public int getErrorFrequency(String error) {
        return questionMapper.countErrorFrequency(error);
    }

    @Override
    public List<TopicDTO> getTopNUserEngageTopics(int n) {
        List<Question> questions = questionMapper.selectAll();
        Map<String, Integer> engagementByTopic = new HashMap<>();

        for (Question question : questions) {
            String tags = question.getTags();
            Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .filter(tag -> !"java".equals(tag) && !tag.isEmpty())
                    .forEach(tag -> {
                        int engagement = question.getViewCount() + question.getCommentCount() +
                                question.getUpVoteCount() + question.getDownVoteCount() +
                                question.getFavoriteCount();
                        engagementByTopic.merge(tag, engagement, Integer::sum);
                    });
        }

        // 将map转换成list，并按参与度降序排序
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(engagementByTopic.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 转换成TopicDTO列表，并取前N个
        List<TopicDTO> topNTopics = entries.stream()
                .limit(n)
                .map(entry -> new TopicDTO(entry.getKey(), (long)entry.getValue()))
                .collect(Collectors.toList());

        return topNTopics;
    }


}
