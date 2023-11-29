package com.digibank.restapi.dto.Bsi.Transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Account{

	@JsonProperty("nama")
	private String nama;

	@JsonProperty("namaBank")
	private String namaBank;

	@JsonProperty("noRekening")
	private String noRekening;
}