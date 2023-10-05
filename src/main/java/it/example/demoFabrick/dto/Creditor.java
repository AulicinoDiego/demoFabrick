package it.example.demoFabrick.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Creditor {

    @NotNull(message = "Il campo name del creditor non può essere nullo")
    private String name;

    @Valid
    @NotNull(message = "Il campo account del creditor non può essere nullo")
    private Account account;

    @Valid
    private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
    
    

}
