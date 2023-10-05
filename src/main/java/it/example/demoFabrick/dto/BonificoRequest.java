package it.example.demoFabrick.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe rappresentante la richiesta di bonifico.
 */
public class BonificoRequest {

	@Valid
    @NotNull(message = "Il campo creditor non può essere nullo")
    private Creditor creditor;

    private String executionDate;

    private String uri;

    @NotNull(message = "Il campo description non può essere nullo")
    @Size(max = 140, message = "La lunghezza massima del campo description è 140") 
    private String description;

    @NotNull(message = "Il campo amount non può essere nullo")
    private Double amount;

    @NotNull(message = "Il campo currency non può essere nullo")
    private String currency;

    @NotNull(message = "Il campo isUrgent non può essere nullo")  
    private Boolean isUrgent;

    @NotNull(message = "Il campo isInstant non può essere nullo")    
    private Boolean isInstant;

    @NotNull(message = "Il campo feeType non può essere nullo")   
    private String feeType;

    private String feeAccountId;

    @Valid
    @NotNull(message = "Il campo taxRelief non può essere nullo")
    private TaxRelief taxRelief;
    
    

	public Creditor getCreditor() {
		return creditor;
	}

	public void setCreditor(Creditor creditor) {
		this.creditor = creditor;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Boolean getIsInstant() {
		return isInstant;
	}

	public void setIsInstant(Boolean isInstant) {
		this.isInstant = isInstant;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeAccountId() {
		return feeAccountId;
	}

	public void setFeeAccountId(String feeAccountId) {
		this.feeAccountId = feeAccountId;
	}

	public TaxRelief getTaxRelief() {
		return taxRelief;
	}

	public void setTaxRelief(TaxRelief taxRelief) {
		this.taxRelief = taxRelief;
	}
    

}
