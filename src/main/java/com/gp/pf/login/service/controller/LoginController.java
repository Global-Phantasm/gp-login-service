package com.gp.pf.login.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gp.pf.login.service.LoginService;
import com.gp.pf.login.service.dto.LoginRequest;
import com.gp.pf.login.service.dto.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
	final private LoginService loginService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void doLogin(@RequestBody LoginRequest loginRequest) {
		loginService.doLogin(loginRequest);
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<LoginResponse> getAllUser() {
		return loginService.loginHistory();
	}

}
