package com.example.analyzer.model;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
//@TableName("person")
public class Person {

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "reputation")
    private int reputation;
    @Column(name = "accept_rate")
    private int acceptRate;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "account_id")
    private int accountId;
}