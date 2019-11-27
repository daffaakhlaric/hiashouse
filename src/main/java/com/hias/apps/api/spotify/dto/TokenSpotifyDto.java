package com.hias.apps.api.spotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenSpotifyDto {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("redirectUri")
	private String redirectUri;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}
