package com.hias.apps.dto;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDto {
	
	@JsonProperty("name")
	private String fullName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;

	@JsonProperty("telp")
	private String telp;

	
	@JsonProperty("isWeb")
	private String isWeb;

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getIsWeb() {
		return isWeb;
	}

	public void setIsWeb(String isWeb) {
		this.isWeb = isWeb;
	}
	
}
