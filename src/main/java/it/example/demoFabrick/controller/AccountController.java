package it.example.demoFabrick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import it.example.demoFabrick.dto.BonificoRequest;
import it.example.demoFabrick.service.IBankingAccountService;


/**
 * Controller per le operazioni relative agli account bancari.
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	
	@Autowired
	private IBankingAccountService bankingAccountService;
	
	/**
     * Ottieni il saldo dell'account.
     *
     * @param accountId ID dell'account.
     * @return Una risposta HTTP contenente il saldo dell'account.
     */
    @GetMapping("/saldo/{accountId}")
    public ResponseEntity<String> getSaldo(@PathVariable @NotNull Long accountId) {
    	return bankingAccountService.getAccountBalance(accountId);
    }
    
    /**
     * Ottieni le transazioni dell'account nel periodo specificato.
     *
     * @param accountId           ID dell'account.
     * @param fromAccountingDate  Data di inizio del periodo.
     * @param toAccountingDate    Data di fine del periodo.
     * @return Una risposta HTTP contenente le transazioni dell'account nel periodo specificato.
     */
    @GetMapping("/transazioni/{accountId}")
    public ResponseEntity<String> getTransazioni(@PathVariable @NotNull Long accountId,
                                 @RequestParam("fromAccountingDate") @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromAccountingDate,
                                 @RequestParam("toAccountingDate") @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toAccountingDate) {
        return bankingAccountService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);   
    }

    /**
     * Effettua un bonifico dall'account.
     *
     * @param accountId       ID dell'account.
     * @param bonificoRequest Dettagli del bonifico.
     * @return Una risposta HTTP confermando l'esito del bonifico.
     */
    @PostMapping("/bonifico/{accountId}")
    public ResponseEntity<String> effettuaBonifico(@PathVariable @NotNull Long accountId,
                                   @RequestBody @NotNull BonificoRequest bonificoRequest) {
        return bankingAccountService.createMoneyTransfer(bonificoRequest, accountId);
    }

}
