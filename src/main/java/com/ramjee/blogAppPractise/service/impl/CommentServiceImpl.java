package com.ramjee.blogAppPractise.service.impl;

import com.ramjee.blogAppPractise.entity.Comment;
import com.ramjee.blogAppPractise.entity.Post;
import com.ramjee.blogAppPractise.exception.BlogApiException;
import com.ramjee.blogAppPractise.exception.ResourceNotFoundException;
import com.ramjee.blogAppPractise.payload.CommentDto;
import com.ramjee.blogAppPractise.repository.CommentRepository;
import com.ramjee.blogAppPractise.repository.PostRepository;
import com.ramjee.blogAppPractise.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(Long postId,CommentDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId + ""));
        post.getComments().add(comment);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId + ""));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId + ""));
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogApiException("Comment does not belong to Post");
        }
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public Set<CommentDto> getAllComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId + ""));
        Set<Comment> comments = commentRepository.findByPostId(postId);
        return  comments.stream().map(comment->modelMapper.map(comment,CommentDto.class)).collect(Collectors.toSet());
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId + ""));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId + ""));
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogApiException("Comment does not belong to Post");
        }
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId + ""));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId + ""));
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogApiException("Comment does not belong to Post");
        }
        commentRepository.deleteById(commentId);
    }
}
