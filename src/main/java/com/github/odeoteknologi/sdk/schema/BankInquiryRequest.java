package com.github.odeoteknologi.sdk.schema;

public class BankInquiryRequest implements BaseSchema {

	protected String accountNumber;
	protected int bankId;
	protected String customerName;
	protected boolean withValidation;

	public BankInquiryRequest(String accountNumber, int bankId, String customerName) {
		this.accountNumber = accountNumber;
		this.bankId = bankId;
		this.customerName = customerName;
		this.withValidation = false;
	}
	
	public BankInquiryRequest(String accountNumber, int bankId, String customerName, boolean withValidation) {
		this.accountNumber = accountNumber;
		this.bankId = bankId;
		this.customerName = customerName;
		this.withValidation = withValidation;
	}

	@Override
	public String getJsonString() {
		json.clear();
		json.put("account_number", this.accountNumber);
		json.put("bank_id", this.bankId);
		json.put("customer_name", this.customerName);
		json.put("with_validation", this.withValidation);
		return json.toJSONString();
	}

}
