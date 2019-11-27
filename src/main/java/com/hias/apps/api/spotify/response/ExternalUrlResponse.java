package com.hias.apps.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalUrlResponse {

	@JsonProperty("spotify")
	private String spotify;

	public String getSpotify() {
		return spotify;
	}

	public void setSpotify(String spotify) {
		this.spotify = spotify;
	}
}
