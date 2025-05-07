package parameta.gateway.service;


import com.parameta.gateway.ws.*;
import jakarta.validation.Valid;
import jakarta.xml.ws.soap.SOAPFaultException;
import org.springframework.stereotype.Service;
import parameta.gateway.dto.request.EmployeeRequest;
import parameta.gateway.dto.response.EmployeeResponse;
import parameta.gateway.exception.EmployeeIsNotOfLegalAgeException;
import parameta.gateway.exception.EmployeeServiceException;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Service
public class EmployeeService {

    private final EmployeesPort employeesPort;

    public EmployeeService(EmployeesPort employeesPort) {
        this.employeesPort = employeesPort;
    }

    public EmployeeResponse registerEmployee(@Valid EmployeeRequest employeeRequest) {

        if (!isLegalAge(employeeRequest.dateOfBirth())) {
            throw new EmployeeIsNotOfLegalAgeException("Employee is not of legal age");
        }

        SaveEmployeeRequest soapEmployeeRequest = getSaveEmployeeRequest(employeeRequest);

        try {
            SaveEmployeeResponse response = employeesPort.saveEmployee(soapEmployeeRequest);
            if (response.isSuccess()) {
                return createResponse(employeeRequest);
            }else {
                // Manejar caso donde success=false pero no es un fault
                throw new EmployeeServiceException("SOAP service returned unsuccessful response: " + response.getMessage());
            }
        } catch (EmployeeFault_Exception e) {
            throw new EmployeeServiceException(e.getMessage());
        } catch (SOAPFaultException e) {
            throw new EmployeeServiceException("Error while communicating with SOAP API: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new EmployeeServiceException("Unexpected error calling SOAP service", e);
        }
    }


    private SaveEmployeeRequest getSaveEmployeeRequest(EmployeeRequest employeeRequest) {
        SaveEmployeeRequest soapEmployeeRequest = new SaveEmployeeRequest();
        soapEmployeeRequest.setName(employeeRequest.name());
        soapEmployeeRequest.setLastName(employeeRequest.lastName());
        soapEmployeeRequest.setDocumentType(DocumentType.valueOf(employeeRequest.documentType().name()));
        soapEmployeeRequest.setIdNumber(employeeRequest.idNumber());
        soapEmployeeRequest.setDateOfBirth(toXMLGregorianCalendar(employeeRequest.dateOfBirth()));
        soapEmployeeRequest.setDateOfJoiningCompany(toXMLGregorianCalendar(employeeRequest.dateOfJoiningCompany()));
        soapEmployeeRequest.setPosition(employeeRequest.position());
        soapEmployeeRequest.setSalary(BigDecimal.valueOf(employeeRequest.salary()));
        return soapEmployeeRequest;
    }

    public boolean isLegalAge(LocalDate dateOfBirth) {
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears() > 18;
    }

    private XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
        try {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (Exception e) {
            throw new RuntimeException("Error converting LocalDate to XMLGregorianCalendar", e);
        }
    }

    public EmployeeResponse createResponse(EmployeeRequest employeeRequest) {
        LocalDate dateOfBirth = employeeRequest.dateOfBirth();
        LocalDate dateOfJoiningCompany = employeeRequest.dateOfJoiningCompany();
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(dateOfBirth, currentDate);
        Period timeAtCompany = Period.between(dateOfJoiningCompany, currentDate);

        return new EmployeeResponse(
                new parameta.gateway.dto.Duration(timeAtCompany.getYears(), timeAtCompany.getMonths(), timeAtCompany.getDays()),
                new parameta.gateway.dto.Duration(age.getYears(), age.getMonths(), age.getDays())
        );
    }
}
