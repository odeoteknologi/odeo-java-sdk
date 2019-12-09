package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public interface BaseSchema {

	JSONObject json = new JSONObject();
	JSONParser parser = new JSONParser();
	
	public String getJsonString();
	
}
