package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public class PaymentGatewayResponse {

	protected long amount;
	protected long fee;
	protected String paymentId;
	protected String referenceId;
	protected long status;
	
	public PaymentGatewayResponse(JSONObject object) {
		this.amount = (long) object.get("amount");
		this.fee = (long) object.get("fee");
		this.paymentId = (String) object.get("payment_id");
		this.referenceId = (String) object.get("reference_id");
		this.status = (long) object.get("status");
	}
	
	public long getAmount() {
		return amount;
	}

	public long getFee() {
		return fee;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public long getStatus() {
		return status;
	}
	
}
