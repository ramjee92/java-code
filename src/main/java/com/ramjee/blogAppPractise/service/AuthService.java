package com.ramjee.blogAppPractise.service;

import com.ramjee.blogAppPractise.payload.LoginDto;
import com.ramjee.blogAppPractise.payload.RegisterDto;

public interface AuthService {

    String verifyLogin(LoginDto dto);

    String registerUser(RegisterDto dto);

}
