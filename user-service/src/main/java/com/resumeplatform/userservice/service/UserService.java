package com.resumeplatform.userservice.service;

import com.resumeplatform.userservice.dto.LoginRequest;
import com.resumeplatform.userservice.dto.LoginResponse;
import com.resumeplatform.userservice.dto.RegisterRequest;
import com.resumeplatform.userservice.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}