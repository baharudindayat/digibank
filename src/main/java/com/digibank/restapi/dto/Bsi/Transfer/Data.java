package com.digibank.restapi.dto.Bsi.Transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("noRekeningDigibank")
	private long noRekeningDigibank;

	@JsonProperty("noRekeningBsi")
	private int noRekeningBsi;

	@JsonProperty("nominal")
	private Object nominal;

	@JsonProperty("catatan")
	private String catatan;
}