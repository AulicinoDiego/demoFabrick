package it.example.demoFabrick.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import it.example.demoFabrick.dto.BonificoRequest;
import it.example.demoFabrick.service.IBankingAccountService;

/**
 * Implementazione del servizio di gestione dell'account bancario.
 */
@Service
public class BankingAccountService implements IBankingAccountService {
	
	@Value("${fabrick.api.banking-account-url}")
    private String fabrickBankingAccountUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(BankingAccountService.class);
	
	private final RestTemplate restTemplate;
	public BankingAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	private static final String accountBalancePath = "/{accountId}/balance";
	private static final String accountTransanctionsPath = "/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
	private static final String accountMoneyTransfersPath = "/{accountId}/payments/money-transfers";
	

	@Override
	public ResponseEntity<String> getAccountBalance(Long accountId) {
		String msg = "";
		try {
			String apiUrl = fabrickBankingAccountUrl + accountBalancePath;
    		ResponseEntity<String> res = restTemplate.getForEntity(apiUrl, String.class, accountId);
    		return res;
    	}catch (HttpClientErrorException e) {
    		msg = String.format("Errore durante la chiamata al servizio Fabrick: %n%s ", e.getResponseBodyAsString());
            logger.error(msg, e);
            return ResponseEntity.status(e.getRawStatusCode()).body(msg);
        } catch (Exception e) {
        	msg = String.format("Errore generico durante la chiamata al servizio Fabrick: %n%s ", e.getMessage());
            logger.error(msg, e);
            return ResponseEntity.internalServerError().body(msg);
        }
	}
	
	@Override
	public ResponseEntity<String> getAccountTransactions(Long accountId, LocalDate fromAccountingDate,
			LocalDate toAccountingDate) {
		String msg = "";
		if (fromAccountingDate.isAfter(toAccountingDate)) {
			msg = "Errore parametri di imput: La data di inizio deve essere precedente alla data di fine";
	        logger.error(msg);
			return ResponseEntity.badRequest().body(msg);
	    }
		try {
            String apiUrl = fabrickBankingAccountUrl + accountTransanctionsPath;
            ResponseEntity<String> res = restTemplate.getForEntity(apiUrl, String.class, accountId, fromAccountingDate, toAccountingDate);
            return res;
        } catch (HttpClientErrorException e) {
        	msg = String.format("Errore durante la chiamata al servizio Fabrick: %n%s ", e.getResponseBodyAsString());
            logger.error(msg, e);
            return ResponseEntity.status(e.getRawStatusCode()).body(msg);
        } catch (Exception e) {
        	msg = String.format("Errore generico durante la chiamata al servizio Fabrick: %n%s ", e.getMessage());
            logger.error(msg, e);
            return ResponseEntity.internalServerError().body(msg);
        }
	}
	
	@Override
	public ResponseEntity<String> createMoneyTransfer(BonificoRequest bonificoRequest,
			Long accountId) {
		String msg = "";
		try {
            String apiUrl = fabrickBankingAccountUrl + accountMoneyTransfersPath;
            ResponseEntity<String> res = restTemplate.postForEntity(apiUrl, bonificoRequest, String.class, accountId);
            return res;
        } catch (HttpClientErrorException e) {
        	msg = String.format("Errore durante la chiamata al servizio Fabrick: %n%s ", e.getResponseBodyAsString());
            logger.error(msg, e);
            return ResponseEntity.status(e.getRawStatusCode()).body(msg);
        } catch (Exception e) {
        	msg = String.format("Errore generico durante la chiamata al servizio Fabrick: %n%s ", e.getMessage());
            logger.error(msg, e);
            return ResponseEntity.internalServerError().body(msg);
        }
	}
}
