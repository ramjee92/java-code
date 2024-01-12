package com.ramjee.blogAppPractise.service.impl;

import com.ramjee.blogAppPractise.entity.Role;
import com.ramjee.blogAppPractise.entity.User;
import com.ramjee.blogAppPractise.exception.BlogApiException;
import com.ramjee.blogAppPractise.payload.LoginDto;
import com.ramjee.blogAppPractise.payload.RegisterDto;
import com.ramjee.blogAppPractise.repository.RoleRepository;
import com.ramjee.blogAppPractise.repository.UserRepository;
import com.ramjee.blogAppPractise.security.JwtTokenProvider;
import com.ramjee.blogAppPractise.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider =jwtTokenProvider;
    }

    @Override
    public String verifyLogin(LoginDto dto) {

        // authenticationManager has authenticate method and we need to pass Authentication object to it ,
        // Authentication is interface , UsernamePasswordToken is implementation class to it and
        // pass username & password as arguments
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword()));
        // next we need to store Authentication object into Security context holder
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateToken(authenticate);
        return token;
    }

    @Override
    public String registerUser(RegisterDto dto) {

       if(userRepository.existsByUsername(dto.getUsername())){
           throw new BlogApiException("Username Already Exists");
       }

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new BlogApiException("Email Already Exists");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findByName("ROLE_USER").get());
        user.setRoles(role);
        userRepository.save(user);
        return "User Registered Successfully Login with your Credentials ";
    }
}
