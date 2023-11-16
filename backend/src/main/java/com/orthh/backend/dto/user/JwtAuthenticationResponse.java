package com.orthh.backend.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtAuthenticationResponse {

  private Long id;
  private String token;
  private String role;

  @Builder
  public JwtAuthenticationResponse(Long id, String token, String role) {
    this.id = id;
    this.token = token;
    this.role = role;
  }
}
