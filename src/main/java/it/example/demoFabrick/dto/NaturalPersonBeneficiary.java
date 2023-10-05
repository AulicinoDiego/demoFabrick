package it.example.demoFabrick.dto;

import javax.validation.constraints.NotNull;

public class NaturalPersonBeneficiary {
	
	@NotNull(message = "Il campo fiscalCode1 del naturalPersonBeneficiary non può essere nullo")
    private String fiscalCode1;

	public String getFiscalCode1() {
		return fiscalCode1;
	}

	public void setFiscalCode1(String fiscalCode1) {
		this.fiscalCode1 = fiscalCode1;
	}

	
}
