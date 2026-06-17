package com.resumeplatform.userservice.controller;

import com.resumeplatform.userservice.dto.LoginRequest;
import com.resumeplatform.userservice.dto.LoginResponse;
import com.resumeplatform.userservice.dto.RegisterRequest;
import com.resumeplatform.userservice.dto.UserResponse;
import com.resumeplatform.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(
            @RequestBody RegisterRequest request) {

        return userService.register(request);
    }


    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return userService.login(request);
    }
}