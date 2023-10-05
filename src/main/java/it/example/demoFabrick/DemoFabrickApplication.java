package it.example.demoFabrick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Classe principale che avvia l'applicazione Spring Boot per la demo Fabrick.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoFabrickApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFabrickApplication.class, args);
	}

}
