package com.ramjee.blogAppPractise.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncrypter {

    public static void main(String[] args) {

        String s1 = "rohit123";
        String s2 = "admin123";

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(s1));
        System.out.println(passwordEncoder.encode(s2));

    }
}
