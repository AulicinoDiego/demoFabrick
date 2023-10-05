package it.example.demoFabrick.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.example.demoFabrick.dto.BonificoRequest;

/**
 * Classe di utilità per la mappatura di stringhe JSON in oggetti BonificoRequest.
 */
public class BonificoRequestMapper {
	
	/**
     * Converte una stringa JSON in un oggetto BonificoRequest.
     *
     * @param bonificoString La stringa JSON rappresentante i dati del bonifico.
     * @return Un oggetto BonificoRequest, o null se la conversione non è riuscita.
     */
	public static BonificoRequest creaBonificoRequest(String bonificoString) {
    	ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(bonificoString, BonificoRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    	
    }
}
