package com.shop.cafe.dto;

public class User {
	private int id;
	private String email, password,createdAt;
	public User(int id, String email, String password, String createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + "]";
	}
	
	
}
