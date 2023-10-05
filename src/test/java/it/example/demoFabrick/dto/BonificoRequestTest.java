package it.example.demoFabrick.dto;

import it.example.demoFabrick.util.BonificoRequestMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di test per la classe BonificoRequest.
 */
public class BonificoRequestTest {
	
	/**
     * Test della creazione di un oggetto BonificoRequest da una stringa JSON valida.
     */
    @Test
    void testCreateBonificoRequest() {
    	// Stringa JSON rappresentante un bonifico valido
        String bonificoString = "{\"creditor\":{\"name\":\"John Doe\",\"account\":{\"accountCode\":\"IT23A0336844430152923804660\",\"bicCode\":\"SELBIT2BXXX\"},\"address\":{\"address\":null,\"city\":null,\"countryCode\":null}},\"executionDate\":\"2019-04-01\",\"uri\":\"REMITTANCE_INFORMATION\",\"description\":\"Payment invoice 75/2017\",\"amount\":800,\"currency\":\"EUR\",\"isUrgent\":false,\"isInstant\":false,\"feeType\":\"SHA\",\"feeAccountId\":\"45685475\",\"taxRelief\":{\"taxReliefId\":\"L449\",\"isCondoUpgrade\":false,\"creditorFiscalCode\":\"56258745832\",\"beneficiaryType\":\"NATURAL_PERSON\",\"naturalPersonBeneficiary\":{\"fiscalCode1\":\"MRLFNC81L04A859L\"},\"legalPersonBeneficiary\":{\"fiscalCode\":null,\"legalRepresentativeFiscalCode\":null}}}\r\n";

        // Test della deserializzazione del JSON in un oggetto BonificoRequest
        BonificoRequest bonificoRequest = BonificoRequestMapper.creaBonificoRequest(bonificoString);

        // Verifica che l'oggetto BonificoRequest non sia nullo
        assertNotNull(bonificoRequest, "Able to create BonificoRequest from JSON");

        // Verifica che i campi dell'oggetto BonificoRequest siano corretti
        assertEquals("John Doe", bonificoRequest.getCreditor().getName());
        assertEquals("2019-04-01", bonificoRequest.getExecutionDate());
        assertEquals("REMITTANCE_INFORMATION", bonificoRequest.getUri());
        assertEquals("Payment invoice 75/2017", bonificoRequest.getDescription());
        assertEquals(800.0, bonificoRequest.getAmount());
        assertEquals("EUR", bonificoRequest.getCurrency());
        assertFalse(bonificoRequest.getIsUrgent());
        assertFalse(bonificoRequest.getIsInstant());
        assertEquals("SHA", bonificoRequest.getFeeType());
        assertEquals("45685475", bonificoRequest.getFeeAccountId());
        assertEquals("L449", bonificoRequest.getTaxRelief().getTaxReliefId());
        assertFalse(bonificoRequest.getTaxRelief().getIsCondoUpgrade());
        assertEquals("56258745832", bonificoRequest.getTaxRelief().getCreditorFiscalCode());
        assertEquals("NATURAL_PERSON", bonificoRequest.getTaxRelief().getBeneficiaryType());
        assertEquals("MRLFNC81L04A859L", bonificoRequest.getTaxRelief().getNaturalPersonBeneficiary().getFiscalCode1());
        assertNull(bonificoRequest.getTaxRelief().getLegalPersonBeneficiary().getFiscalCode());
        assertNull(bonificoRequest.getTaxRelief().getLegalPersonBeneficiary().getLegalRepresentativeFiscalCode());
    }
    
}
