package com.shop.cafe.dto;

public class Login {
	private String name, email, token;
	private Long createdAt;

	public Login(String name, String email, String token, long createdAt) {
		this.name = name;
		this.email = email;
		this.token = token;
		this.createdAt = createdAt;
	}

	public Login() {}

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

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Login [name=" + name + ", email=" + email + ", token=" + token + ", createdAt=" + createdAt + "]";
	}

}
