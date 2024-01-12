package com.ramjee.blogAppPractise.controller;

import com.ramjee.blogAppPractise.payload.CommentDto;
import com.ramjee.blogAppPractise.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId,@Valid @RequestBody CommentDto dto){

       return new ResponseEntity<>(commentService.createComment(postId,dto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable Long postId, @PathVariable Long commentId){

       return commentService.getCommentById(postId, commentId);
    }

    @GetMapping("/{postId}/comments")
    public Set<CommentDto> getAllComments(@PathVariable Long postId){
        return commentService.getAllComments(postId);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable Long postId, @PathVariable Long commentId,@Valid @RequestBody CommentDto dto){
        return commentService.updateComment(postId, commentId, dto);
    }
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }



}
