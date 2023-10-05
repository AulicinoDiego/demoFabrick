package it.example.demoFabrick.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import it.example.demoFabrick.model.TransactionLog;
import it.example.demoFabrick.service.ITransactionService;


/**
 * Aspect per la gestione delle transazioni.
 */
@Aspect
@Component
public class TransactionsAspect {

    @Autowired
    private ITransactionService transactionService;
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionsAspect.class);
    
    /**
     * Metodo che intercetta le chiamate al metodo `getAccountTransactions` di `BankingAccountService`,
     * registra le transazioni nel database e restituisce la risposta originale.
     *
     * @param joinPoint Il punto di giunzione durante l'esecuzione del metodo target.
     * @return La risposta originale del metodo target.
     * @throws Throwable Eccezione generica che può essere lanciata durante l'esecuzione.
     */
    @Around("execution(* it.example.demoFabrick.service.impl.BankingAccountService.getAccountTransactions(..))")
    public Object logAndSaveTransactions(ProceedingJoinPoint joinPoint) throws Throwable {
    	Long arg = (long) 0;
    	if (joinPoint.getArgs().length > 0) {
    		arg = (Long) joinPoint.getArgs()[0];
    	}
        // Chiama il metodo target
        ResponseEntity<String> originalResponse = (ResponseEntity<String>) joinPoint.proceed();

        // Se la chiamata originale ha avuto successo, elabora le transazioni e salva nel database
        
        List<TransactionLog> transactions = handleTransactions(originalResponse.getBody());
        Long accountId = arg;
        transactions.forEach(t -> {
        	t.setUserId(accountId);
        	transactionService.saveTransaction(t);});
        
        //mi faccio restituire dal db i log salvati
        List<TransactionLog> transLog = transactionService.getAllTransactions();
        transLog.forEach(t -> logger.info(t.toString()));

        return originalResponse;
    }
    
    
    /**
     * Metodo privato per gestire l'elaborazione delle transazioni dal corpo della risposta.
     *
     * @param responseBody Il corpo della risposta contenente le informazioni sulle transazioni.
     * @return Una lista di oggetti TransactionLog rappresentanti le transazioni.
     * @throws Throwable Eccezione generica che può essere lanciata durante l'elaborazione.
     */
    private List<TransactionLog> handleTransactions(String responseBody) throws Throwable{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        JsonNode payload = objectMapper.readTree(responseBody).get("payload");
        List<TransactionLog> transactions = new ArrayList<>();

        if (payload != null && payload.isObject()) {
            JsonNode list = payload.get("list");
            if (list != null && list.isArray()) {
                transactions = objectMapper.readValue(list.traverse(), new TypeReference<List<TransactionLog>>() {});
            } else {
                // Gestisci il caso in cui il campo "list" non è presente o non è un array JSON
                logger.error("Il campo 'list' non è presente o non è un array JSON.");
            }
        } else {
            // Gestisci il caso in cui il campo "payload" non è presente o non è un oggetto JSON
            logger.error("Il campo 'payload' non è presente o non è un oggetto JSON.");
        }

        return transactions;
    }
}

