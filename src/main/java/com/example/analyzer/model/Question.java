package com.example.analyzer.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("questions")
public class Question {

//    @TableId(value = "area_id")
    @TableField("question_id")
    private int questionId;
    @TableField("title")
    private String title;
    @TableField("asker_id")
    private int askerId;
    @TableField("tags")
    private String tags;
    @TableField("body")
    private String body;
    @TableField("view_count")
    private int viewCount;
    @TableField("comment_count")
    private int commentCount;
    @TableField("down_vote_count")
    private int downVoteCount;
    @TableField("up_vote_count")
    private int upVoteCount;
    @TableField("favorite_count")
    private int favoriteCount;
    @TableField("score")
    private int score;
    @TableField("answer_count")
    private int answerCount;
    @TableField("link")
    private String link;
}
