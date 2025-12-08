package projeto_garcom.com.demo.common.exceptions;

public class HttpResponseException extends RuntimeException {
    private final int statusCode;

    public HttpResponseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
