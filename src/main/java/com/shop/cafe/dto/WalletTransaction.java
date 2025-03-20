package com.shop.cafe.dto;

import com.shop.cafe.enums.TransactionType;

public class WalletTransaction {
	private String account;
	private int amount;
	private TransactionType type;
	
	public WalletTransaction() {}
	public WalletTransaction(String account, int amount, TransactionType type) {
		setAccount(account);
		setAmount(amount);
		setType(type);
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "WalletTransaction [account=" + account + ", amount=" + amount + ", type=" + type + "]";
	}
	
}
