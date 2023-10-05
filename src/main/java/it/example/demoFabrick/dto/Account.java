package it.example.demoFabrick.dto;
import javax.validation.constraints.NotNull;

public class Account {

    @NotNull(message = "Il campo accountCode dell'account non può essere nullo")
    private String accountCode;

    private String bicCode;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}
    
}
