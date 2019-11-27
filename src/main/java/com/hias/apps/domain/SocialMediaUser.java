package com.hias.apps.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="social_media_user")
public class SocialMediaUser {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="social_id", nullable = false)
	private Long socialId;
	
	@Column(name="user_id", nullable = false)
	private Long userId;
	
	@Column(name="account_name", nullable = false)
	private String accountName;
	
	@Column(name="follower")
	private BigInteger follower;
	
	@Column(name="following")
	private BigInteger following;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="url", nullable = true)
	private String url;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigInteger getFollower() {
		return follower;
	}

	public void setFollower(BigInteger follower) {
		this.follower = follower;
	}

	public BigInteger getFollowing() {
		return following;
	}

	public void setFollowing(BigInteger following) {
		this.following = following;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getSocialId() {
		return socialId;
	}

	public void setSocialId(Long socialId) {
		this.socialId = socialId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
