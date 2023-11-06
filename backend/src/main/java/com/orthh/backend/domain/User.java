package com.orthh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {
	private int id;
	private String name;
	private String role;
	private String email;
    private String password;

    public User(int id, String name, String role, String email, String password){
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }
}