package parameta.soapdb.exception;

public class EmployeeAlreadyExist extends RuntimeException {
    public EmployeeAlreadyExist(String s) {
        super(s);
    }
}
