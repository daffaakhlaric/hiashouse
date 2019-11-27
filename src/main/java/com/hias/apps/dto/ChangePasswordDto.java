package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordDto {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("confirmationPassword")
	private String ConfirmationPassword;
	
	public String getConfirmationPassword() {
		return ConfirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		ConfirmationPassword = confirmationPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
