package com.github.odeoteknologi.sdk.schema;

public class OauthRequest implements BaseSchema {

	protected final String GRANT_TYPE = "client_credentials";
	protected String clientId;
	protected String clientSecret;
	protected String scope;
	
	public OauthRequest(String clientId, String clientSecret) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = "";
	}
	
	public OauthRequest(String clientId, String clientSecret, String scope) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = scope;
	}
	
	public void setCredentials(String clientId, String clientSecret, String scope) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = scope;
	}
	
	@Override
	public String getJsonString() {
		json.clear();
		json.put("client_id", this.clientId);
		json.put("client_secret", this.clientSecret);
		json.put("grant_type", this.GRANT_TYPE);
		json.put("scope", this.scope);
		return json.toJSONString();
	}
	
}
