package com.resumeplatform.userservice.service;

import com.resumeplatform.userservice.dto.RegisterRequest;
import com.resumeplatform.userservice.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);
}