package com.github.odeoteknologi.sdk;

import com.github.odeoteknologi.sdk.schema.OauthRequest;
import com.github.odeoteknologi.sdk.schema.OauthResponse;
import com.github.odeoteknologi.sdk.util.Logger;
import com.github.odeoteknologi.sdk.util.exception.InvalidStatusCodeException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class OdeoApi {

    protected String clientId;
    protected String clientSecret;
    protected String signingKey;
    protected String accessToken;
    protected String baseUrl;
    protected CloseableHttpClient httpClient;
    protected BasicResponseHandler responseHandler;
    protected JSONObject headers;
    protected JSONObject json;
    protected JSONParser parser;
    protected MessageDigest digest;
    protected OauthRequest oauth;
    protected Logger logger;

    public OdeoApi() {
        this.parser = new JSONParser();
        this.headers = new JSONObject();
        this.httpClient = HttpClients.createDefault();
        this.responseHandler = new BasicResponseHandler();
        this.oauth = new OauthRequest("", "");
        this.logger = new Logger();
    }

    public void setApiCredentials(String clientId, String clientSecret, String signingKey) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.signingKey = signingKey;

        this.oauth.setCredentials(clientId, clientSecret, "");
    }

    public void setProductionEnvironment() {
        this.baseUrl = "https://api.v2.odeo.co.id";
    }

    public void setStagingEnvironment() {
        this.baseUrl = "http://api.v2.staging.odeo.co.id";
    }

    public long getUnixTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public String generateBodyHash(String requestBody) {
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
            byte[] rawHash  = this.digest.digest(requestBody.getBytes());
            return Base64.getEncoder().encodeToString(rawHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String generateSignature(String httpMethod, String path, String query, String accessToken, String timestamp, String requestBody) throws NoSuchAlgorithmException, InvalidKeyException {
        String bodyHash = this.generateBodyHash(requestBody);
        String stringToSign = String.join(":", new String[] {httpMethod.toUpperCase(), path, query, accessToken, timestamp, bodyHash});

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(this.signingKey.getBytes(), "HmacSHA256");
        mac.init(secretKey);

        return Base64.getEncoder().encodeToString(mac.doFinal(stringToSign.getBytes()));
    }

    public boolean isValidSignature(String signature, String httpMethod, String path, String query, String accessToken, String timestamp, String requestBody) throws InvalidKeyException, NoSuchAlgorithmException {
        String validSignature = this.generateSignature(httpMethod, path, query, accessToken, timestamp, requestBody);
        return this.isValidSignature(signature, validSignature);
    }

    public boolean isValidSignature(String signature, String signatureToCompare) {
        return signature.equals(signatureToCompare);
    }

    public JSONObject createApiRequest(String method, String path, String requestBody, boolean withHeader) throws Exception {
        JSONObject response = null;
        switch (method) {
            case "GET":
                response = this.executeApiGet(path, requestBody, withHeader);
                break;
            case "POST":
                response = this.executeApiPost(path, requestBody, withHeader);
                break;
        }
        return response;
    }

    public OauthResponse requestAccessToken() throws Exception {
        this.json = this.createApiRequest("POST", "/oauth2/token", this.oauth.getJsonString(), false);
        OauthResponse response = new OauthResponse(this.json);
        this.accessToken = response.getAccessToken();
        return response;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public JSONObject executeApiPost(String path, String requestBody, boolean withHeader) throws Exception {
        HttpPost request = new HttpPost(this.baseUrl + path);

        if (withHeader) {
            this.setRequestHeaders(request, "POST", path, requestBody);
        }

        StringEntity params = new StringEntity(requestBody);
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        return this.logAndResponse(requestBody, response);
    }

    public JSONObject executeApiGet(String path, String requestBody, boolean withHeader) throws Exception {
        HttpGet request = new HttpGet(this.baseUrl + path);

        if (withHeader) {
            this.setRequestHeaders(request, "GET", path, requestBody);
        }

        HttpResponse response = httpClient.execute(request);

        return this.logAndResponse(requestBody, response);
    }

    private void setRequestHeaders(HttpGet request, String method, String path, String requestBody) throws InvalidKeyException, NoSuchAlgorithmException {
        String timestamp = String.valueOf(this.getUnixTimestamp());
        String signature = this.generateSignature(method, path, "", this.accessToken, timestamp, requestBody);

        request.addHeader("Authorization", "Bearer " + this.accessToken);
        request.addHeader("X-Odeo-Timestamp", timestamp);
        request.addHeader("X-Odeo-Signature", signature);
        this.logHeaders(timestamp, signature);
    }

    private void setRequestHeaders(HttpPost request, String method, String path, String requestBody) throws InvalidKeyException, NoSuchAlgorithmException {
        String timestamp = String.valueOf(this.getUnixTimestamp());
        String signature = this.generateSignature(method, path, "", this.accessToken, timestamp, requestBody);

        request.addHeader("Authorization", "Bearer " + this.accessToken);
        request.addHeader("X-Odeo-Timestamp", String.valueOf(this.getUnixTimestamp()));
        request.addHeader("X-Odeo-Signature", signature);
        this.logHeaders(timestamp, signature);
    }

    private JSONObject logAndResponse(String requestBody, HttpResponse response) throws IOException, ParseException, org.json.simple.parser.ParseException, InvalidStatusCodeException {
        this.logRequest(requestBody, response);
        this.checkResponseStatusCode(response, this.logger.getResponseBody());
        return (JSONObject) this.parser.parse(this.logger.getResponseBody());
    }

    private void checkResponseStatusCode(HttpResponse response, String responseString) throws  InvalidStatusCodeException, IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            return;
        }
        throw new InvalidStatusCodeException(responseString);
    }

    private void logHeaders(String timestamp, String signature) {
        this.headers.clear();
        this.headers.put("Authorization", "Bearer " + this.accessToken);
        this.headers.put("X-Odeo-Timestamp", timestamp);
        this.headers.put("X-Odeo-Signature", signature);
    }

    private void logRequest(String requestBody, HttpResponse response) throws IOException {
        this.logger.clear();
        this.logger.setResponseStatus(response.getStatusLine().getStatusCode());
        this.logger.setRequestBody(requestBody);
        this.logger.setRequestHeaders(this.headers.toJSONString());
        this.logger.setResponseBody(this.responseHandler.handleResponse(response));
    }

    public String getLatestLog() {
        return this.logger.getLatestLog();
    }

}
