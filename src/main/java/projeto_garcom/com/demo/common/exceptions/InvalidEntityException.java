package projeto_garcom.com.demo.common.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class InvalidEntityException extends RuntimeException {
    private Map<String, String> errors = new HashMap<>();

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    };
}