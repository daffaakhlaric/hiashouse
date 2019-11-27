package com.hias.apps.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowersResponse {

	@JsonProperty("href")
	private String href;
	
	@JsonProperty("total")
	private Integer total;
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
