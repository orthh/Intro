package com.orthh.backend.controller;

import com.orthh.backend.domain.User;
import com.orthh.backend.dto.user.UserLoginReqDto;
import com.orthh.backend.dto.user.UserLoginResDto;
import com.orthh.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name="userAPI" , description = "사용자 관련 API")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

	List<User> users = new ArrayList<User>();

    @Operation(summary = "로그인 성공 실패 여부를 가져옵니다.")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResDto> login(@RequestBody UserLoginReqDto request) {
        log.info("start login with userEmail = {}", request.getEmail());
        UserLoginResDto user = userService.authenticate(request.getEmail(), request.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "모든 사용자 정보를 가져옵니다.")
	@GetMapping(value = "/getUsers")
	public List<User> getUsers() {
        log.info("start getUsers");
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