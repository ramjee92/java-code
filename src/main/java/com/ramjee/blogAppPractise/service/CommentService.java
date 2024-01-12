package com.ramjee.blogAppPractise.service;

import com.ramjee.blogAppPractise.payload.CommentDto;

import java.util.Set;

public interface CommentService {

    CommentDto createComment(Long postId,CommentDto dto);

    CommentDto getCommentById(Long postId,Long commentId);

    Set<CommentDto> getAllComments(Long postId);

    CommentDto updateComment(Long postId,Long commentId,CommentDto dto);

    void deleteComment(Long postId,Long commentId);


}
