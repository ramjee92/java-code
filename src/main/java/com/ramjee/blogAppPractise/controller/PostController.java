package com.ramjee.blogAppPractise.controller;

import com.ramjee.blogAppPractise.payload.PostDto;
import com.ramjee.blogAppPractise.payload.PostResponse;
import com.ramjee.blogAppPractise.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto){
        return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
            postService.deletePost(postId);
            return new ResponseEntity<>("Post Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
           return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId,@Valid @RequestBody PostDto dto){
        return new ResponseEntity<>(postService.updatePost(postId,dto),HttpStatus.OK);
    }

    // http://localhost:8080/api/posts?sortDir=asc&sortBy=id&pageNo=1&pageSize=4
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(required = false,defaultValue ="asc") String sortDir,
                                    @RequestParam(required = false,defaultValue = "id") String sortBy,
                                    @RequestParam(required = false,defaultValue = "0") int pageNo,
                                    @RequestParam(required = false,defaultValue = "4") int pageSize){
        return postService.getAllPosts(sortDir, sortBy, pageNo, pageSize);
    }


}
