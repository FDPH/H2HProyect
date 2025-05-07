package parameta.gateway.exception;

public class SoapClientException extends RuntimeException {
    public SoapClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
