package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialMediaDto {

	@JsonProperty("token")
	private String code;
	
	@JsonProperty("socialMedia")
	private String socialMediaName;
	
	@JsonProperty("accountName")
	private String accountName;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
