package com.github.odeoteknologi.sdk.schema;

import org.json.simple.JSONObject;

public class OauthResponse {

	protected String accessToken;
	protected long expiresIn;
	protected String scope;
	protected String tokenType;
	
	public OauthResponse(JSONObject object) {
		this.accessToken = (String) object.get("access_token");
		this.expiresIn =  (Long) object.get("expires_in");
		this.scope = (String) object.get("scope");
		this.tokenType = (String) object.get("token_type");
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public String getTokenType() {
		return tokenType;
	}
	
}
