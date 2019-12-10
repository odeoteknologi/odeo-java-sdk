package com.github.odeoteknologi.sdk.schema;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.github.odeoteknologi.sdk.instance.Bank;

public class BankResponse {

	protected ArrayList<Bank> banks;
	
	public BankResponse(JSONObject object) {
		this.banks =  new ArrayList<Bank>();
		JSONArray arr = (JSONArray) object.get("banks");
		Iterator i = arr.iterator();
		while(i.hasNext()) {
			JSONObject obj = (JSONObject) i.next();
			banks.add(new Bank((long) obj.get("bank_id"), (String) obj.get("name")));
		}
	}

	public ArrayList<Bank> getBanks() {
		return banks;
	}
	
}