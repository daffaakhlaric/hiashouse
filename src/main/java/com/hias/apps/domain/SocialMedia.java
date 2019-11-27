package com.hias.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="social_media")
public class SocialMedia {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="social_media_name", nullable = false)
	private String socialMediaName;
	
	@Column(name="social_media_url", nullable = true)
	private String url;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
