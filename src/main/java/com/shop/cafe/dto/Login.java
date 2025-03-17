package com.shop.cafe.dto;

public class Login {
	private String name, email, token,createdAt;

	public Login(String name, String email, String token, String createdAt) {
		super();
		this.name = name;
		this.email = email;
		this.token = token;
		this.createdAt = createdAt;
	}

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Login [name=" + name + ", email=" + email + ", token=" + token + ", createdAt=" + createdAt + "]";
	}

}
