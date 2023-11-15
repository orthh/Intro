package com.orthh.backend.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class JwtAuthenticationResponse {
  private String token;

  @Builder
  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }
}
