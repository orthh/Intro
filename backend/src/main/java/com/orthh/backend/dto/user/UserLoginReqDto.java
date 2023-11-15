package com.orthh.backend.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginReqDto {
  private String email;
  private String password;
}
