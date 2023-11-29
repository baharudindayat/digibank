package com.digibank.restapi.dto.Bsi.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("nama")
	private String nama;

	@JsonProperty("namaBank")
	private String namaBank;

	@JsonProperty("noRekening")
	private String noRekening;
}