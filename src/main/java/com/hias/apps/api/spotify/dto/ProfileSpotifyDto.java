package com.hias.apps.api.spotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileSpotifyDto {

	@JsonProperty("token")
	private String token;
	
	@JsonProperty("accountName")
	private String accountName;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
