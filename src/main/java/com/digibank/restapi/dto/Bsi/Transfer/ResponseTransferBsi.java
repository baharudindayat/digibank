package com.digibank.restapi.dto.Bsi.Transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTransferBsi {

	@JsonProperty("Account")
	private Account account;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private int status;
}