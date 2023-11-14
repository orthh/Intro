package com.orthh.backend.dto.user;

import com.orthh.backend.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResDto {
    private Long id;
	private String nickname;
	private String role;
	private String email;

    public UserLoginResDto(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

}
