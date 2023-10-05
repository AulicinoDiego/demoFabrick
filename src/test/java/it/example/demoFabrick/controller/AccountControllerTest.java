package it.example.demoFabrick.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.example.demoFabrick.dto.BonificoRequest;
import it.example.demoFabrick.service.IBankingAccountService;
import it.example.demoFabrick.util.BonificoRequestMapper;

/**
 * Test unitari per la classe {@link AccountController}.
 */
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

    @Mock
    private IBankingAccountService bankingAccountService;

    @InjectMocks
    private AccountController accountController;
    
    /**
     * Testa il metodo {@link AccountController#getSaldo(Long)}.
     */
    @Test
    public void testGetSaldo() {
        Long accountId = 123L;
        when(bankingAccountService.getAccountBalance(accountId)).thenReturn(ResponseEntity.ok("Balance"));

        ResponseEntity<String> response = accountController.getSaldo(accountId);
        
        // Verifica che la risposta abbia uno stato OK e contenga il messaggio "Saldo"
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance", response.getBody());
    }
    
    /**
     * Testa il metodo {@link AccountController#getTransazioni(Long, LocalDate, LocalDate)}.
     */
    @Test
    public void testGetTransazioni() {
        Long accountId = 123L;
        LocalDate fromAccountingDate = LocalDate.of(2023, 1, 1);
        LocalDate toAccountingDate = LocalDate.of(2023, 12, 31);
        when(bankingAccountService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate)).thenReturn(ResponseEntity.ok("Transactions"));

        ResponseEntity<String> response = accountController.getTransazioni(accountId, fromAccountingDate, toAccountingDate);
        
        // Verifica che la risposta abbia uno stato OK e contenga il messaggio "Transazioni"
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transactions", response.getBody());
    }
    
    /**
     * Testa il metodo {@link AccountController#effettuaBonifico(Long, BonificoRequest)}.
     */
    @Test
    public void testEffettuaBonifico() {
        Long accountId = 123L;
        
        // Stringa che rappresenta un JSON valido per un bonifico
        String bonificoString = "{\"creditor\":{\"name\":\"John Doe\",\"account\":{\"accountCode\":\"IT23A0336844430152923804660\",\"bicCode\":\"SELBIT2BXXX\"},\"address\":{\"address\":null,\"city\":null,\"countryCode\":null}},\"executionDate\":\"2019-04-01\",\"uri\":\"REMITTANCE_INFORMATION\",\"description\":\"Payment invoice 75/2017\",\"amount\":800,\"currency\":\"EUR\",\"isUrgent\":false,\"isInstant\":false,\"feeType\":\"SHA\",\"feeAccountId\":\"45685475\",\"taxRelief\":{\"taxReliefId\":\"L449\",\"isCondoUpgrade\":false,\"creditorFiscalCode\":\"56258745832\",\"beneficiaryType\":\"NATURAL_PERSON\",\"naturalPersonBeneficiary\":{\"fiscalCode1\":\"MRLFNC81L04A859L\"},\"legalPersonBeneficiary\":{\"fiscalCode\":null,\"legalRepresentativeFiscalCode\":null}}}\r\n";
        BonificoRequest bonificoRequest = BonificoRequestMapper.creaBonificoRequest(bonificoString); 
        // Verifica che l'oggetto BonificoRequest non sia nullo
        assertNotNull(bonificoRequest, "Able to create BonificoRequest from JSON");
        // Simula la risposta del servizio di bonifico
        when(bankingAccountService.createMoneyTransfer(bonificoRequest, accountId)).thenReturn(ResponseEntity.ok("Money transfer successful"));
        // Chiama il controller per effettuare un bonifico
        ResponseEntity<String> response = accountController.effettuaBonifico(accountId, bonificoRequest);
        // Verifica che la risposta abbia uno stato OK e contenga il messaggio "Bonifico effettuato con successo"
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Money transfer successful", response.getBody());
    }
    
    /**
     * Testa il metodo {@link AccountController#effettuaBonifico(Long, BonificoRequest)} con una richiesta non valida.
     */
    @Test
    public void testEffettuaBonificoWithInvalidRequest() {
        Long accountId = 123L;
        
        // Stringa che rappresenta un bonifico con JSON non valido
        String bonificoString = "{\\\"creditor\\\":{\\\"account\\\":{\\\"accountCode\\\":\\\"IT23A0336844430152923804660\\\",\\\"bicCode\\\":\\\"SELBIT2BXXX\\\"},\\\"address\\\":{\\\"address\\\":null,\\\"city\\\":null,\\\"countryCode\\\":null}},\"executionDate\":\"2019-04-01\",\"uri\":\"REMITTANCE_INFORMATION\",\"description\":\"Payment invoice 75/2017\",\"amount\":800,\"currency\":\"EUR\",\"isUrgent\":false,\"isInstant\":false,\"feeType\":\"SHA\",\"feeAccountId\":\"45685475\",\"taxRelief\":{\"taxReliefId\":\"L449\",\"isCondoUpgrade\":false,\"creditorFiscalCode\":\"56258745832\",\"beneficiaryType\":\"NATURAL_PERSON\",\"naturalPersonBeneficiary\":{\"fiscalCode1\":\"MRLFNC81L04A859L\"},\"legalPersonBeneficiary\":{\"fiscalCode\":null,\"legalRepresentativeFiscalCode\":null}}}\r\n";
        BonificoRequest bonificoRequest = BonificoRequestMapper.creaBonificoRequest(bonificoString); 
        // Verifica che l'oggetto BonificoRequest sia nullo (a causa dell'input JSON non valido)
        assertNull(bonificoRequest, "\"Expected BonificoRequest to be null for invalid JSON\"");

    }
}
