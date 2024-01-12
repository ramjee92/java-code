package com.ramjee.blogAppPractise.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthResponse {

    private String accessToken;
    private String tokenType="Bearer";
}
