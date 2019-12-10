package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public interface BaseSchema {

	JSONObject json = new JSONObject();

	public String getJsonString();
	
}
