package it.example.demoFabrick.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TaxRelief {

    @NotNull(message = "Il campo taxReliefId non può essere nullo")
    private String taxReliefId;

    @NotNull(message = "Il campo isCondoUpgrade del taxRelief non può essere nullo")
    private Boolean isCondoUpgrade;

    @NotNull(message = "Il campo creditorFiscalCode del taxRelief non può essere nullo")
    private String creditorFiscalCode;

    @NotNull(message = "Il campo beneficiaryType del taxRelief non può essere nullo")
    private String beneficiaryType;

    @Valid
    @NotNull(message = "Il campo naturalPersonBeneficiary non può essere nullo")
    private NaturalPersonBeneficiary naturalPersonBeneficiary;

    @Valid
    @NotNull(message = "Il campo legalPersonBeneficiary non può essere nullo")
    private LegalPersonBeneficiary legalPersonBeneficiary;

	public String getTaxReliefId() {
		return taxReliefId;
	}

	public void setTaxReliefId(String taxReliefId) {
		this.taxReliefId = taxReliefId;
	}

	public Boolean getIsCondoUpgrade() {
		return isCondoUpgrade;
	}

	public void setIsCondoUpgrade(Boolean isCondoUpgrade) {
		this.isCondoUpgrade = isCondoUpgrade;
	}

	public String getCreditorFiscalCode() {
		return creditorFiscalCode;
	}

	public void setCreditorFiscalCode(String creditorFiscalCode) {
		this.creditorFiscalCode = creditorFiscalCode;
	}

	public String getBeneficiaryType() {
		return beneficiaryType;
	}

	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
		return naturalPersonBeneficiary;
	}

	public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiary) {
		this.naturalPersonBeneficiary = naturalPersonBeneficiary;
	}

	public LegalPersonBeneficiary getLegalPersonBeneficiary() {
		return legalPersonBeneficiary;
	}

	public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
		this.legalPersonBeneficiary = legalPersonBeneficiary;
	}
    
    


}
