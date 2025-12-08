package projeto_garcom.com.demo.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(GenericException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", ex.getStatusCode());

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(body);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEntity(InvalidEntityException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("errors", ex.getErrors());
        body.put("status", 400);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpected(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Erro interno inesperado.");
        body.put("error", ex.getMessage());
        body.put("status", 500);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }
}
