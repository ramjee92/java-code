package com.ramjee.blogAppPractise.payload;

import com.ramjee.blogAppPractise.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 6,message = "title must be atleast 6 characters long")
    private String title;
    @NotEmpty
    @Size(min = 8,message = "description must be atleast 8 characters long")
    private String description;
    @NotEmpty
    @Size(min = 10,message = "content must be atleast 10 characters long")
    private String content;

    private Set<CommentDto> comments;

}
