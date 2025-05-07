package parameta.soapdb.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import parameta.soapdb.SaveEmployeeRequest;
import parameta.soapdb.SaveEmployeeResponse;
import parameta.soapdb.service.EmployeeService;

@Endpoint
public class EmployeeEndpoint {

    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final String NAMESPACE_URI = "http://parameta.com/soapdb/employee";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
        return employeeService.saveEmployee(request);
    }
}
