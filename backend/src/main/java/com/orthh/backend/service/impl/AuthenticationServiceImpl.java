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

/**
 * 회원 관련 서비스
 *
 * @author orthh
 * @since 2023-11-15
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtServiceImpl jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * 회원가입 method
   *
   * @param UserJoinReqDto
   * @return JwtAuthenticationResponse
   */
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
    User newUser = userRepository.findByEmail(request.getEmail());
    String jwt = jwtService.generateToken(newUser); // 사용자 정보를 바탕으로 JWT를 생성
    return JwtAuthenticationResponse.builder()
        .role(newUser.getRole())
        .id(newUser.getUserid())
        .token(jwt)
        .build();
  }

  /**
   * 로그인 method
   * 
   * @param UserLoginReqDto
   * @return JwtAuthenticationResponse
   */
  @Override
  public JwtAuthenticationResponse signin(UserLoginReqDto request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword())); // authenticate 메서드가 내부적으로 PasswordEncoder 실행
    User user = userRepository.findByEmail(request.getEmail());
    String jwt = jwtService.generateToken(user);
    return JwtAuthenticationResponse.builder()
        .role(user.getRole())
        .id(user.getUserid())
        .token(jwt)
        .build();
  }
}
