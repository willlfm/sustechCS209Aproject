package com.example.analyzer.model;

//import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "answer")
@NoArgsConstructor
public class Answer {
    @Id
    @Column(name = "answer_id")
    private int answerId;
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "score")
    private int score;
    @Column(name = "is_accepted")
    private boolean isAccepted;
    @Column(name = "owner_user_id")
    private int ownerUserId;
    @Column(name = "creation_date")
    private Date creationDate;

}