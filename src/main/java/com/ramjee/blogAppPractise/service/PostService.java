package com.ramjee.blogAppPractise.service;

import com.ramjee.blogAppPractise.payload.PostDto;
import com.ramjee.blogAppPractise.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto dto);

    void deletePost(Long postId);

    PostDto updatePost(Long postId,PostDto dto);

    PostDto getPostById(Long postId);

    PostResponse getAllPosts(String sortDir, String sortBy, int pageNo , int pageSize);


}
