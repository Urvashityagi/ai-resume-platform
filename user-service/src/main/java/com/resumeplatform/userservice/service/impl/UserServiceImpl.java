package com.resumeplatform.userservice.service.impl;

import com.resumeplatform.userservice.dto.RegisterRequest;
import com.resumeplatform.userservice.dto.UserResponse;
import com.resumeplatform.userservice.entity.Role;
import com.resumeplatform.userservice.entity.User;
import com.resumeplatform.userservice.repository.UserRepository;
import com.resumeplatform.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }
}