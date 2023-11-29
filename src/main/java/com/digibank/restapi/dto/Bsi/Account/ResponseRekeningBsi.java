package com.digibank.restapi.dto.Bsi.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRekeningBsi{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private int status;
}