package com.hias.apps.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

@Entity
@Table(name="user_registration")
public class UserRegister {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@JsonIgnore
	private Long id;

	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="fullname", nullable = false)
	private String fullName;

	@Column(name="telp", nullable= false)
	private String telp;
	
	@Column(name="password", nullable=false)
	private String password;

	@Column(name="is_confirm", nullable=false)
	@Type(type="yes_no")
	private boolean isConfirm;
	
	@Column(name="token", nullable=false)
	private String token;
	
	@Column(name="expired_token", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date expiredToken;
	
	@OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "userRegister")
	private User users;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConfirm() {
		return isConfirm;
	}


	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiredToken() {
		return expiredToken;
	}

	public void setExpiredToken(Date expiredToken) {
		this.expiredToken = expiredToken;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

}
