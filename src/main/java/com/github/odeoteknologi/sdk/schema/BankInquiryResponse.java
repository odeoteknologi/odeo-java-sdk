package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public class BankInquiryResponse {

	protected String accountName;
	protected String accountNumber;
	protected String bankAccountInquiryId;
	protected long bankId;
	protected String createdAt;
	protected String customerName;
	protected long fee;
	protected long status;
	protected double validity;
	
	public BankInquiryResponse(JSONObject object) {
		this.accountName = (String) object.get("account_name");
		this.accountNumber = (String) object.get("account_number");
		this.bankAccountInquiryId = (String) object.get("bank_account_inquiry_id");
		this.bankId = (long) object.get("bank_id");
		this.createdAt = (String) object.get("created_at");
		this.customerName = (String) object.get("customer_name");
		this.fee = (long) object.get("fee");
		this.status = (long) object.get("status");
		this.validity = Double.parseDouble(object.get("validity").toString());
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getBankAccountInquiryId() {
		return bankAccountInquiryId;
	}

	public long getBankId() {
		return bankId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getCustomerName() {
		return customerName;
	}

	public long getFee() {
		return fee;
	}

	public long getStatus() {
		return status;
	}

	public double getValidity() {
		return validity;
	}
	
}
