package com.orthh.backend.service.impl;

import com.orthh.backend.repository.UserRepository;
import com.orthh.backend.service.JwtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * jwt 인증관련 서비스
 *
 * @author 김혁
 * @since 2023.11.14
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class JwtUserServiceImpl implements JwtUserService {
  private final UserRepository userRepository;

  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username);
      }
    };
  }
}
