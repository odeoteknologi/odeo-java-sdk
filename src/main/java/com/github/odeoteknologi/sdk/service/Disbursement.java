package com.github.odeoteknologi.sdk.service;

import com.github.odeoteknologi.sdk.OdeoApi;
import com.github.odeoteknologi.sdk.schema.BalanceResponse;
import com.github.odeoteknologi.sdk.schema.BankInquiryRequest;
import com.github.odeoteknologi.sdk.schema.BankInquiryResponse;
import com.github.odeoteknologi.sdk.schema.BankResponse;
import com.github.odeoteknologi.sdk.schema.DisbursementRequest;
import com.github.odeoteknologi.sdk.schema.DisbursementResponse;

public class Disbursement extends OdeoApi {

	public BankInquiryResponse requestBankAccountInquiry(BankInquiryRequest request) throws Exception {
		this.json = this.createApiRequest("POST", "/dg/v1/bank-account-inquiry", request.getJsonString(), true);
		return new BankInquiryResponse(this.json);
	}

	public BankInquiryResponse requestBankAccountInquiry(String accountNumber, int bankId, String customerName, boolean withValidation) throws Exception {
		BankInquiryRequest request = new BankInquiryRequest(accountNumber, bankId, customerName, withValidation);
		return this.requestBankAccountInquiry(request);
	}

	public BankInquiryResponse requestBankAccountInquiry(String accountNumber, int bankId, String customerName) throws Exception {
		BankInquiryRequest request = new BankInquiryRequest(accountNumber, bankId, customerName);
		return this.requestBankAccountInquiry(request);
	}
	
	public DisbursementResponse requestDisbursement(DisbursementRequest request) throws Exception {
		this.json = this.createApiRequest("POST", "/dg/v1/disbursements", request.getJsonString(), true);
		return new DisbursementResponse(this.json);
	}

	public DisbursementResponse requestDisbursement(String accountNumber, int amount, int bankId, String customerName, String referenceId) throws Exception {
		DisbursementRequest request = new DisbursementRequest(accountNumber, amount, bankId, customerName, referenceId);
		return this.requestDisbursement(request);
	}

	public DisbursementResponse requestDisbursement(String accountNumber, int amount, int bankId, String customerName, String referenceId, String description) throws Exception {
		DisbursementRequest request = new DisbursementRequest(accountNumber, amount, bankId, customerName, referenceId, description);
		return this.requestDisbursement(request);
	}

	public DisbursementResponse checkByReferenceId(String referenceId) throws Exception {
		this.json = this.createApiRequest("GET", "/dg​/v1​/disbursements​/reference-id​/" + referenceId, "", true);
		return new DisbursementResponse(this.json);
	}
	
	public DisbursementResponse checkByDisbursementId(String disbursementId) throws Exception {
		this.json = this.createApiRequest("GET", "/dg/v1/disbursements/" + disbursementId, "", true);
		return new DisbursementResponse(this.json);
	}
	
	public BalanceResponse getBalance() throws Exception {
		this.json = this.createApiRequest("GET", "/cash/me/balance", "", true);
		return new BalanceResponse(this.json);
	}
	
	public BankResponse getBankList() throws Exception {
		this.json = this.createApiRequest("GET", "/dg/v1/banks", "", true);
		return new BankResponse(this.json);
	}
	
}
