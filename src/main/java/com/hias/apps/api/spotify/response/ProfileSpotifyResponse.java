package com.hias.apps.api.spotify.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileSpotifyResponse {

	@JsonProperty("display_name")
	private String diplayName;
	
	@JsonProperty("external_urls")
	private ExternalUrlResponse externalUrl;
	
	@JsonProperty("followers")
	private FollowersResponse follower;
	
	@JsonProperty("href")
	private String href;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("images")
	private List<ImagesResponse> images;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("uri")
	private String uri;
	
	public String getDiplayName() {
		return diplayName;
	}

	public void setDiplayName(String diplayName) {
		this.diplayName = diplayName;
	}

	public ExternalUrlResponse getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(ExternalUrlResponse externalUrl) {
		this.externalUrl = externalUrl;
	}

	public FollowersResponse getFollower() {
		return follower;
	}

	public void setFollower(FollowersResponse follower) {
		this.follower = follower;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ImagesResponse> getImages() {
		return images;
	}

	public void setImages(List<ImagesResponse> images) {
		this.images = images;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
