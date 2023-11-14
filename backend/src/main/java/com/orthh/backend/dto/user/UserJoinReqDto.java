package com.orthh.backend.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinReqDto {
  private String email;
  private String password;
  private String nickname;
}
