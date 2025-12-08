package projeto_garcom.com.demo.common.exceptions;

public class GenericException extends RuntimeException {
  private final int statusCode;

  public GenericException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public GenericException(String message) {
    super(message);
    this.statusCode = 400;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
