package it.example.demoFabrick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entità che rappresenta un log di transazione.
 */
@Entity
@Table(name = "transaction_log")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column 
	private Long userId;
	@Column
    private String transactionId;
	@Column
    private String operationId;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate accountingDate;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate valueDate;
	@Column
	private BigDecimal amount;
	@Column
	private String currency;
	@Column
	private String description;
    
    
	public TransactionLog() {
	}

	
	public TransactionLog(Long userId, String transactionId, String operationId, LocalDate accountingDate,
			LocalDate valueDate, BigDecimal amount, String currency, String description) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.operationId = operationId;
		this.accountingDate = accountingDate;
		this.valueDate = valueDate;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
	}



	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public LocalDate getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(LocalDate accountingDate) {
		this.accountingDate = accountingDate;
	}
	public LocalDate getValueDate() {
		return valueDate;
	}
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
     * Restituisce la rappresentazione in formato stringa dell'oggetto TransactionLog.
     *
     * @return Rappresentazione in formato stringa
     */
	@Override
	public String toString() {
		return "TransactionLog [id=" + id + ", userId=" + userId + ", transactionId=" + transactionId + ", operationId="
				+ operationId + ", accountingDate=" + accountingDate + ", valueDate=" + valueDate + ", amount=" + amount
				+ ", currency=" + currency + ", description=" + description + "]";
	}
    
}
