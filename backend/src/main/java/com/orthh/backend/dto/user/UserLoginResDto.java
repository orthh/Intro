package com.orthh.backend.dto.user;

import com.orthh.backend.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResDto {
    private int id;
	private String name;
	private String role;
	private String email;

    public UserLoginResDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

}
