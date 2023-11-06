package com.orthh.backend.controller;

import com.orthh.backend.domain.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name="userAPI" , description = "사용자 관련 API")
@RequestMapping("/api/users")
public class UserController {

	List<User> users = new ArrayList<User>();
	{
		users.add(new User(1,"orthh", "ADMIN", "witchicken2@gmail.com"));
		users.add(new User(2,"TechGeekNext-User2", "SUPERVISOR", "user2@test.com"));
		users.add(new User(3,"TechGeekNext-User3", "USER", "user3@test.com"));
		users.add(new User(4,"TechGeekNext-User4", "USER", "user4@test.com"));
	}

    @Operation(summary = "모든 사용자 정보를 가져옵니다.")
	@GetMapping(value = "/getUsers")
	public List<User> getUsers() {
		return users;
	}

    @Operation(summary = "ID를 이용해 특정 사용자 정보를 가져옵니다.", 
               parameters = {@Parameter(name = "id", required = true, description = "사용자 ID")})
	@GetMapping(value = "/getUser/{id}")
	public User getUserById(@PathVariable(value = "id") int id) {
		return users.stream().filter(x -> x.getId()==(id)).collect(Collectors.toList()).get(0);
	}

    @Operation(summary = "역할을 이용해 사용자 정보를 가져옵니다.", 
               parameters = {@Parameter(name = "role", required = true, description = "사용자 역할")})
	@GetMapping(value = "/getUser/role/{role}")
	public List<User> getUserByRole(@PathVariable(value = "role") String role) {
		return users.stream().filter(x -> x.getRole().equalsIgnoreCase(role))
				.collect(Collectors.toList());
	}
}