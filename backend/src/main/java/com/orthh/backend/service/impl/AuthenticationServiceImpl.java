package com.orthh.backend.service.impl;

import com.orthh.backend.domain.User;
import com.orthh.backend.dto.user.JwtAuthenticationResponse;
import com.orthh.backend.dto.user.UserJoinReqDto;
import com.orthh.backend.dto.user.UserLoginReqDto;
import com.orthh.backend.repository.UserRepository;
import com.orthh.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * 사용자 인증에 관련된 서비스를 제공하는 클래스
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtServiceImpl jwtService;
  private final AuthenticationManager authenticationManager;

  // 회원가입
  @Override
  public JwtAuthenticationResponse signup(UserJoinReqDto request) {
    User user =
        User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .nickname(request.getNickname())
            .role("ROLE_USER") // 기본적으로 "ROLE_USER"로 설정
            .build();
    userRepository.save(user);
    String jwt = jwtService.generateToken(user); // 사용자 정보를 바탕으로 JWT를 생성
    return JwtAuthenticationResponse.builder().token(jwt).build();
  }

  @Override
  // 로그인
  public JwtAuthenticationResponse signin(UserLoginReqDto request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword())); // authenticate 메서드가 내부적으로 PasswordEncoder 실행
    User user = userRepository.findByEmail(request.getEmail());
    String jwt = jwtService.generateToken(user);
    return JwtAuthenticationResponse.builder()
        .role(user.getRole())
        .id(user.getId())
        .token(jwt)
        .build();
  }
}
