package com.shop.cafe.dto;

import com.shop.cafe.util.RandomStringNumberGenerator;

public class Wallet {
	private String account, email, username;
	private int balance;
	
	public Wallet() {}
	
	public Wallet(String email) {
		// 계좌 번호 자동 생성
		setAccount(RandomStringNumberGenerator.generateRandomStringNumber(14));
		setEmail(email);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
