package parameta.gateway.exception;

import java.net.URI;

public class EmployeeIsNotOfLegalAgeException extends RuntimeException {
    public static final URI TYPE = URI.create("https://parameta.gateway.com/docs/errors/employee-is-not-of-legal-age");
    public static final String TITLE = "Employee is not of legal age";
    public EmployeeIsNotOfLegalAgeException(String message) {
        super(message);
    }
}
