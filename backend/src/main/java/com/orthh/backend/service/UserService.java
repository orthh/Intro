package com.orthh.backend.service;

import org.apache.el.stream.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orthh.backend.domain.User;
import com.orthh.backend.dto.user.UserJoinReqDto;
import com.orthh.backend.dto.user.UserLoginReqDto;
import com.orthh.backend.dto.user.UserLoginResDto;
import com.orthh.backend.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /*
     * post 회원가입
     */
    public String join(UserJoinReqDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            return "아이디가 존재합니다.";
        }else{
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            User newUser = User.builder()
                .email(request.getEmail()).password(encodedPassword).nickname(request.getNickname())
                .build();
            userRepository.save(newUser);
            return "회원가입 성공!";
        }
    }

    /*
     * post 로그인
     */
    public UserLoginResDto authenticate(UserLoginReqDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new UserLoginResDto(user);
        }
        return null;
    }
}