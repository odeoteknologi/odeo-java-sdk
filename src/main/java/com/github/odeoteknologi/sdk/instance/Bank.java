package com.github.odeoteknologi.sdk.instance;

public class Bank {

	protected long bankId;
	protected String name;
	
	public Bank(long bankId, String name) {
		this.bankId = bankId;
		this.name = name;
	}
	
	public long getBankId() {
		return bankId;
	}
	
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
