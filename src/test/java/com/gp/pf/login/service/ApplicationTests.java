package com.gp.pf.login.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.pf.login.service.dto.LoginRequest;
import com.gp.pf.login.service.repository.LoginRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ApplicationTests {

	@Container
	static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;

	
	@Autowired
	LoginRepository repository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.url", () -> container.getReplicaSetUrl("embedded"));
	}

	@Test
	void shouldCreateUser() throws Exception {
		LoginRequest loginRequest = getLoginRequest();
		int initialSize = repository.findAll().size();
		String requestString = mapper.writeValueAsString(loginRequest);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString)
		);
		Assertions.assertEquals(repository.findAll().size(),initialSize+1);
	}

	private LoginRequest getLoginRequest() {
		return LoginRequest.builder().username("dheerajkumar04")
				.password("HFDTD%^D^%$E%D%^")
				.time("Mon, 08 Jan 2024 13:53:24 GMT")
				.device("'Not_A Brand';v='8', 'Chromium';v='120', 'Google Chrome';v='120'")
				.build();
	}

}
