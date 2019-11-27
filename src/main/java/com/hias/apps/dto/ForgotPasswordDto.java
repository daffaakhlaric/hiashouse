package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordDto {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("confirmEmail")
	private String confirmEmail;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConnfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
}
