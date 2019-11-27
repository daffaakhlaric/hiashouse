package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResendEmailRegisterDto {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("isWeb")
	private String isWeb;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsWeb() {
		return isWeb;
	}

	public void setIsWeb(String isWeb) {
		this.isWeb = isWeb;
	}
	
}
