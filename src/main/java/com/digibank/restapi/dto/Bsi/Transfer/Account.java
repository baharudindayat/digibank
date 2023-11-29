package com.digibank.restapi.dto.Bsi.Transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account{

	@JsonProperty("nama")
	private String nama;

	@JsonProperty("namaBank")
	private String namaBank;

	@JsonProperty("noRekening")
	private String noRekening;
}