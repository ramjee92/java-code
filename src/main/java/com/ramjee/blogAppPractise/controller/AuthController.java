package com.ramjee.blogAppPractise.controller;

import com.ramjee.blogAppPractise.payload.JwtAuthResponse;
import com.ramjee.blogAppPractise.payload.LoginDto;
import com.ramjee.blogAppPractise.payload.RegisterDto;
import com.ramjee.blogAppPractise.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signIn"})
    public ResponseEntity<JwtAuthResponse> verifyLogin(@RequestBody LoginDto dto){

        String token = authService.verifyLogin(dto);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = {"/register","/signUp"})
    public ResponseEntity<String> createRegistration(@RequestBody RegisterDto dto){
        return new ResponseEntity<>(authService.registerUser(dto),HttpStatus.OK);
    }

}
