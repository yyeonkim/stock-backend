package com.shop.cafe.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
	@JsonProperty WITHDRAWAL("출금"),
	@JsonProperty DEPOSIT("입금");
	
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
