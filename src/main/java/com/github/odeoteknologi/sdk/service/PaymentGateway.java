package com.github.odeoteknologi.sdk.service;

import com.github.odeoteknologi.sdk.OdeoApi;
import com.github.odeoteknologi.sdk.schema.PaymentGatewayResponse;

public class PaymentGateway extends OdeoApi {

	public PaymentGatewayResponse checkByReferenceId(String referenceId) throws Exception {
		this.json = this.createApiRequest("GET", "/pg/v1/payment/reference-id/" + referenceId, "", true);
		return new PaymentGatewayResponse(this.json);
	}
	
	public PaymentGatewayResponse checkByPaymentId(String paymentId) throws Exception {
		this.json = this.createApiRequest("GET", "/pg/v1/payment/" + paymentId, "", true);
		return new PaymentGatewayResponse(this.json);
	}
	
}
