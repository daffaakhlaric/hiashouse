package com.hias.apps.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagesResponse {

	@JsonProperty("height")
	private Integer height;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("width")
	private Integer width;
	
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

}
