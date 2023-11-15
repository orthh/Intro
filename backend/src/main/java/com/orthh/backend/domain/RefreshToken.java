package com.orthh.backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 3)
public class RefreshToken {

  @Id private String refreshToken;

  private String email;

  public RefreshToken(String email, String refreshToken) {
    this.email = email;
    this.refreshToken = refreshToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getEmail() {
    return email;
  }
}
