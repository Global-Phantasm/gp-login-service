package com.gp.pf.login.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("portfolio_login_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Login {
	@Id
	private String id;
	private String username;
	private String password;
	private String time;
	private String device;
	private String status;
}
