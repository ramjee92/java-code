package com.ramjee.blogAppPractise.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;

}
