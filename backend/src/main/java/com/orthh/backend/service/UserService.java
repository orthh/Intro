package com.orthh.backend.service;

import org.springframework.stereotype.Service;

import com.orthh.backend.domain.User;
import com.orthh.backend.dto.user.UserLoginResDto;
import com.orthh.backend.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserLoginResDto authenticate(String email, String password) {
        User user = userMapper.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return new UserLoginResDto(user);
        }
        return null;
    }
}