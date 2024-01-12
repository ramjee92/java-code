package com.ramjee.blogAppPractise.repository;

import com.ramjee.blogAppPractise.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Set<Comment> findByPostId(Long postId);

}
