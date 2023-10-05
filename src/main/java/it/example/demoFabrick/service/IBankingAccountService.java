package it.example.demoFabrick.service;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import it.example.demoFabrick.dto.BonificoRequest;

/**
 * Interfaccia per il servizio di gestione dell'account bancario.
 */
public interface IBankingAccountService {
	
	/**
     * Ottiene il saldo dell'account bancario.
     *
     * @param accountId ID dell'account bancario
     * @return ResponseEntity contenente la risposta del servizio
     */
	ResponseEntity<String> getAccountBalance(Long accountId);
	
	/**
     * Ottiene le transazioni dell'account bancario in un intervallo di date.
     *
     * @param accountId           ID dell'account bancario
     * @param fromAccountingDate  Data di inizio dell'intervallo
     * @param toAccountingDate    Data di fine dell'intervallo
     * @return ResponseEntity contenente la risposta del servizio
     */
	ResponseEntity<String> getAccountTransactions(Long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate);
	
	/**
     * Effettua un bonifico dall'account bancario.
     *
     * @param bonificoRequest Dati del bonifico
     * @param accountId       ID dell'account bancario
     * @return ResponseEntity contenente la risposta del servizio
     */
	ResponseEntity<String> createMoneyTransfer(BonificoRequest bonificoRequest, Long accountId);
	
}
