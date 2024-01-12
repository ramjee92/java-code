package com.ramjee.blogAppPractise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;
    @ManyToOne()
    @JoinColumn(name = "posts_id",referencedColumnName = "id",nullable = false)
    private Post post;
    }
