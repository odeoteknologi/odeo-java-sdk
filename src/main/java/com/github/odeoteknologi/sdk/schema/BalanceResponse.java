package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public class BalanceResponse {

	protected long cashAmount;
	protected String cashFormattedAmount;
	protected long lockedCashAmount;
	protected String lockedCashFormattedAmount;
	
	public BalanceResponse(JSONObject object) {
		JSONObject lockedCashObject = (JSONObject) object.get("locked_cash");
		JSONObject cashObject = (JSONObject) object.get("cash");
		this.cashAmount = (long) cashObject.get("amount");
		this.cashFormattedAmount = (String) cashObject.get("formatted_amount");
		this.lockedCashAmount = (long) lockedCashObject.get("amount");
		this.lockedCashFormattedAmount = (String) lockedCashObject.get("formatted_amount");
	}

	public long getCashAmount() {
		return cashAmount;
	}

	public String getCashFormattedAmount() {
		return cashFormattedAmount;
	}

	public long getLockedCashAmount() {
		return lockedCashAmount;
	}

	public String getLockedCashFormattedAmount() {
		return lockedCashFormattedAmount;
	}
	
}
