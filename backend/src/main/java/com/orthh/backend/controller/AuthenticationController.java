package com.orthh.backend.controller;

import com.orthh.backend.dto.user.JwtAuthenticationResponse;
import com.orthh.backend.dto.user.UserJoinReqDto;
import com.orthh.backend.dto.user.UserLoginReqDto;
import com.orthh.backend.service.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 관련 controller
 *
 * @author 김혁
 * @since 2023-11-15
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "jwtAPI", description = "인증 관련 API")
public class AuthenticationController {

  private final AuthenticationServiceImpl authenticationService;

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody UserJoinReqDto request) {
    return ResponseEntity.ok(authenticationService.signup(request));
  }

  @Operation(summary = "로그인")
  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody UserLoginReqDto request) {
    return ResponseEntity.ok(authenticationService.signin(request));
  }
}
