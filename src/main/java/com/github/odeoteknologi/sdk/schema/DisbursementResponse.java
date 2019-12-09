package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public class DisbursementResponse {

	protected String accountNumber;
	protected long amount;
	protected long bankId;
	protected String createdAt;
	protected String customerName;
	protected String description;
	protected String disbursementId;
	protected long fee;
	protected String referenceId;
	protected long status;
	
	public DisbursementResponse(JSONObject object) {
		this.accountNumber = (String) object.get("account_number");
		this.amount = (long) object.get("amount");
		this.bankId = (long) object.get("bank_id");
		this.createdAt = (String) object.get("created_at");
		this.customerName = (String) object.get("customer_name");
		this.description = (String) object.get("description");
		this.disbursementId = (String) object.get("disbursement_id");
		this.fee = (long) object.get("fee");
		this.referenceId = (String) object.get("reference_id");
		this.status = (long) object.get("status");
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public long getAmount() {
		return amount;
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

	public String getDescription() {
		return description;
	}

	public String getDisbursementId() {
		return disbursementId;
	}

	public long getFee() {
		return fee;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public long getStatus() {
		return status;
	}
	
}
