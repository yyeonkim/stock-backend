package com.shop.cafe.enums;

public enum TransactionType {
	WITHDRAWAL("출금"),
	DEPOSIT("입금");
	
	private String type;

	TransactionType(String type) {
		setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
