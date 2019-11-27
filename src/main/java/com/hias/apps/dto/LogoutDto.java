package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoutDto {

	@JsonProperty("token")
	private String token;

	@JsonProperty("username")
	private String username;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
