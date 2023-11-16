package com.orthh.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtUserService {

  UserDetailsService userDetailsService();
}
