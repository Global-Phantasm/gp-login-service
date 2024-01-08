package com.gp.pf.login.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gp.pf.login.service.dto.LoginRequest;
import com.gp.pf.login.service.dto.LoginResponse;
import com.gp.pf.login.service.model.Login;
import com.gp.pf.login.service.repository.LoginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
	final private LoginRepository loginRepository;

	public void doLogin(LoginRequest userRequest) {		
		Login login = Login.builder()
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.time(userRequest.getTime())
				.device(userRequest.getDevice())
				.build();
		loginRepository.save(login);
		log.info("Login id: {} initiated",login.getId());
	}
	
	public List<LoginResponse> loginHistory() {		
		List<Login> login = loginRepository.findAll();
		return login.stream().map(this::mapToUserResponse).toList();
	}

	private LoginResponse mapToUserResponse(Login login) {
		return LoginResponse.builder()
				.id(login.getId())
				.username(login.getUsername())
				.device(login.getDevice())
				.status(login.getStatus())
				.time(login.getTime())
				.build();
	}
}
