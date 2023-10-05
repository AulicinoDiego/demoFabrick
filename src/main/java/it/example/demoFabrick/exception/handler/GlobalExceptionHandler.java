package it.example.demoFabrick.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Classe che gestisce le eccezioni globali nell'applicazione.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
     * Gestisce le eccezioni di validazione dei parametri di input.
     *
     * @param ex l'eccezione di validazione
     * @return una risposta HTTP con uno stato BAD_REQUEST e un messaggio di errore
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        logger.error(errorMessage, ex);
        return ResponseEntity.badRequest().body("Errore nei parametri di input: " + errorMessage);
    }
    
    /**
     * Gestisce le eccezioni di tipo non corrispondente nei parametri di input.
     *
     * @param ex l'eccezione di tipo non corrispondente
     * @return una risposta HTTP con uno stato BAD_REQUEST e un messaggio di errore
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Errore nel parametro di input '%s'. Il valore '%s' non può essere convertito a '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        logger.error(errorMessage, ex);
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
