# odeo-java-sdk
Odeo Java SDK for interacting with https://api.v2.odeo.co.id/

## Quickstart
This SDK can be obtained by adding it as a maven dependency, cloning the source into your project, or by downloading one of the precompiled JARs from the [releases page on Github](https://github.com/odeoteknologi/odeo-java-sdk/releases).

**IF YOU USE THE JAR, you'll also need to include several dependencies:**
1. [Apache HttpComponents 4.5.10](https://hc.apache.org/httpcomponents-client-4.5.x/index.html)
2. [JSON Simple 1.1.1](https://code.google.com/archive/p/json-simple/)

## Installation
You can use this project by defining it as a dependency within your Java project. See [release page](https://github.com/odeoteknologi/odeo-java-sdk/releases) to find the versions of the project.

##### Maven
````xml
<dependency>
    <groupId>com.github.odeoteknologi</groupId>
    <artifactId>odeo-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
````

##### Gradle
This SDK is stored in Bintray JCenter Repository. 
```gradle
repositories {
    jcenter() // add jcenter as one of the repository
}

dependencies {
    compile 'com.github.odeoteknologi:odeo-java-sdk:1.0.0'
}
```
## Building
The SDK uses Gradle for its build system. Running `gradle build` from the root of the repository will compile the SDK.
## Usage
### OdeoApi
`OdeoApi` class provides the basic function needed to configure API calls and Signature generation.
```java
import com.github.odeoteknologi.sdk.OdeoApi;

// ... your code
OdeoApi api = new OdeoApi(); // initialize class
disbursement.setApiCredentials(clientId, clientSecret, signingKey);
```
Set API Base url:
```java
String baseUrl = "https://api.v2.dev.odeo.co.id/"
api.setBaseUrl(baseUrl);
```
Requesting and setting access token:
```java
api.requestAccessToken(); // request access token also set the access token for your api calls
OauthResponse oauthResponse = api.requestAccessToken(); // request access token also set the access token for your api calls
String accessToken = oauthResponse.getAccessToken(); // you can store your accessToken using OauthResponse class prepared for this SDK
api.setAccessToken(accessToken); // set access token
```
Creating API calls:
```java
api.executeApiGet(apiPath, "", true);
api.executeApiPost(apiPath, requestBody, false); // set boolean false to request without headers
api.createApiRequest("GET", apiPath, "", true); // set boolean true to set headers as documented
api.createApiRequest("POST", apiPath, requestBody, true);
```
To help you access the response easily, all of API calls return `JSONObject` (see [JSON Simple](https://code.google.com/archive/p/json-simple/)):
```java
JSONObject object = api.executeApiPost(apiPath, "", true);
String value = (String) object.get("value");
```
Use the schemas prepared to help you create and store api calls:
```java
OauthResponse oauthResponse = api.requestAccessToken();
BankInquiryRequest request = new BankInquiryRequest(accountNo, bankId, customerName); // create inquiry request object
JSONObject response = api.executeApiPost(apiPath, request.getJsonString(), true);
```
Use the class to help you create request using your own client:
```java
String timestamp = Long.toString(api.getUnixTimestamp());
BankInquiryRequest request = new BankInquiryRequest("123456", 1, "CustomerName"); // create inquiry request object
String requestBody = request.getJsonString();
String signature = api.generateSignature("POST", "/dg/v1/banks", "", accessToken, timestamp, requestBody);
```
Comparing callback signature:
```java
String generatedSignature = api.generateSignature(method, path, "", accessToken, timestamp, requestBody);
if (api.isValidSignature(callbackSignature, generatedSignature)) {
    // ... if valid
} else {
    // ... if invalid
}
// or
if (api.isValidSignature(callbackSignature, method, path, "", accessToken, timestamp, requestBod)) {
    // ... if valid
} else {
    // ... if invalid
}
```
`OdeoApi` also logs your recent api calls. This will help you to check the request headers and body that was generated when you're calling an API request:
```java
// ...
System.out.println(api.getLatestLog());
// ...
```
### Disbursement
`Disbursement` class extends from `OdeoApi` so you can use the public method provided by `OdeoApi`. 
`Disbursement` created to help developers to call Disbursement API Request easily.
```java
import com.github.odeoteknologi.sdk.service.Disbursement;
// ...
Disbursement disbursement = new Disbursement();
api.setStagingEnvironment(); // set environment
disbursement.setApiCredentials(clientId, clientSecret, SigningKey);
disbursement.requestAccessToken();
// ...
```
Bank List `/dg/v1/banks`:
```java
BankResponse response = disbursement.getBankList();
ArrayList<Bank> banks = response.getBanks();
```
Bank Inquiry API calls `/dg​/v1​/bank-account-inquiry`:
```java
// ...
BankInquiryRequest inquiryRequest = new BankInquiryRequest(accountNumber, bankId, customerName);
BankInquiryResponse inquiryResponse = disbursement.requestBankAccountInquiry(inquiryRequest);
String accountName = inquiryResponse.getAccountName();
String accountNumber = inquiryResponse.getAccountNumber();
// ...
```
Disbursement API calls `/dg/v1/disbursements`:
```java
// ...
DisbursementRequest request = new DisbursementRequest(accountNumber, amount, bankId, customerName, referenceId, description);
DisbursementRequest response = disbursement.requestDisbursement(request);
// ...
```
Check Disbursement status:
```java
// ...
DisbursementResponse response = disbursement.checkByDisbursementId(disbursementId); // /dg/v1/disbursements/{disbursement_id}
// or
DisbursementResponse response = disbursement.checkByReferenceId(referenceId); // /dg/v1/disbursements/reference-id/{reference_id}
// ...
```

### PaymentGateway
`PaymentGateway` also extends from `OdeoApi`. This class created to help developers to call Payment Gateway API and can be used to validate callbacks.
```java
import com.github.odeoteknologi.sdk.service.PaymentGateway;
// ...
PaymentGateway pg = new PaymentGateway();
pg.setStagingEnvironment(); // set environment
pg.setApiCredentials(clientId, clientSecret, SigningKey);
pg.requestAccessToken();
// ...
```
Check Payment status:
```java
// ...
PaymentGatewayResponse response = pg.checkByPaymentId(paymentId);
// or
PaymentGatewayResponse response = pg.checkByReferenceId(referenceId);
// ...
```
Validating callbacks:
```java
// ...
String generatedSignature = pg.generateSignature(method, path, "", "", timestamp, requestBody);
if (pg.isValidSignature(callbackSignature, generatedSignature)) {
    // ... if valid
} else {
    // ... if invalid
}
// or
if (pg.isValidSignature(callbackSignature, method, path, "", "", timestamp, requestBod)) {
    // ... if valid
} else {
    // ... if invalid
}
// ...
```
