package com.orthh.backend.service;

import com.orthh.backend.dto.user.JwtAuthenticationResponse;
import com.orthh.backend.dto.user.UserJoinReqDto;
import com.orthh.backend.dto.user.UserLoginReqDto;

public interface AuthenticationService {
  
  JwtAuthenticationResponse signup(UserJoinReqDto request);

  JwtAuthenticationResponse signin(UserLoginReqDto request);

}
