package com.hias.apps.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile extends User{
	
	@JsonProperty("fullName")
	private String fullname;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("userName")
	private String username;

	@JsonIgnore
	private String password;

	@JsonProperty("email")
	private String email;

	@JsonProperty("birthdate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthdate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("registerDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date registerDate;

	@JsonProperty("category")
	private String categoryUser;
	
	@JsonProperty("imageProfile")
	private String imageProfile;
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getCategoryUser() {
		return categoryUser;
	}

	public void setCategoryUser(String categoryUser) {
		this.categoryUser = categoryUser;
	}

	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

	
}
