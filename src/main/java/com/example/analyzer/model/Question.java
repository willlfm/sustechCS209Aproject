package com.example.analyzer.model;

//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@Entity
@Table(name = "questions")
//@TableName("questions")
public class Question {

//    @TableId(value = "area_id")
    @Id
    @Column(name = "question_id")
//    @TableField("question_id")
    private int questionId;
    @Column(name = "title")
//    @TableField("title")
    private String title;
    @Column(name = "asker_id")
    private int askerId;
    @Column(name = "tags")
    private String tags;
    @Column(name = "body")
    private String body;
    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "comment_count")
    private int commentCount;
    @Column(name = "down_vote_count")
    private int downVoteCount;
    @Column(name = "up_vote_count")
    private int upVoteCount;
    @Column(name = "favorite_count")
    private int favoriteCount;
    @Column(name = "score")
    private int score;
    @Column(name = "answer_count")
    private int answerCount;
    @Column(name = "link")
    private String link;
    @Column(name = "creation_date")
    private Date creationDate;
}
