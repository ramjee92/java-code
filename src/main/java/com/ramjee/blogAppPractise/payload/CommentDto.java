package com.ramjee.blogAppPractise.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private Long id;
    @NotEmpty
    @Size(min = 3,message = "name must be atleast 3 characters long")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8,message = "body must be atleast 8 characters long")
    private String body;
}
