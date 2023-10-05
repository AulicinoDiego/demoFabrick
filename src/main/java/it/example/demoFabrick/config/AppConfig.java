package it.example.demoFabrick.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configurazione dell'applicazione.
 */
@Configuration
public class AppConfig {

    @Value("${fabrick.api.base-url}")
    private String fabrickBaseUrl;

    @Value("${fabrick.api.api-key}")
    private String fabrickApiKey;
    
    /**
     * Bean per la creazione di un oggetto RestTemplate configurato.
     *
     * @param builder Il builder utilizzato per configurare il RestTemplate.
     * @return Un oggetto RestTemplate con la radice URI, gli header predefiniti e la chiave API configurati.
     */
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .rootUri(fabrickBaseUrl)
            .defaultHeader("Auth-Schema", "S2S")
            .defaultHeader("Api-Key", fabrickApiKey)
            .build();
    }
}
