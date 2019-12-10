package com.github.odeoteknologi.sdk.util;

public class Logger {

    protected String requestHeaders;
    protected String requestBody;
    protected String responseBody;
    protected int responseStatus;

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void clear() {
        this.requestHeaders = "";
        this.requestBody = "";
        this.responseBody = "";
        this.responseStatus = 0;
    }

    public String getLatestLog() {
        return "Headers: '" + this.requestHeaders + "'\n" +
                "Body: '" + this.requestBody + "'\n" +
                "Response: '" + this.responseBody + "'\n" +
                "Status: " + this.responseStatus;
    }

}
