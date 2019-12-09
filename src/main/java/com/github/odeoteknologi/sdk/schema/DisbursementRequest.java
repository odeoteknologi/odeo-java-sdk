package com.github.odeoteknologi.sdk.schema;

public class DisbursementRequest implements BaseSchema {

	private String accountNumber;
	private int amount;
	private int bankId;
	private String customerName;
	private String description;
	private String referenceId;
	
	public DisbursementRequest(String accountNumber, int amount, int bankId, String customerName, String referenceId) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.bankId = bankId;
		this.customerName = customerName;
		this.referenceId = referenceId;
		this.description = "";
	} 
	
	public DisbursementRequest(String accountNumber, int amount, int bankId, String customerName, String referenceId, String description) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.bankId = bankId;
		this.customerName = customerName;
		this.referenceId = referenceId;
		this.description = description;
	}

	@Override
	public String getJsonString() {
		json.clear();
		json.put("account_number", this.accountNumber);
		json.put("amount", this.amount);
		json.put("bank_id", this.bankId);
		json.put("customer_name", this.customerName);
		json.put("description", this.description);
		json.put("reference_id", this.referenceId);
		return json.toJSONString();
	}
	
}
