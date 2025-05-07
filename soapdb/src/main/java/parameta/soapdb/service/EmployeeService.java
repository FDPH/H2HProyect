package parameta.soapdb.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import parameta.soapdb.SaveEmployeeRequest;
import parameta.soapdb.SaveEmployeeResponse;
import parameta.soapdb.exception.EmployeeAlreadyExist;
import parameta.soapdb.model.Employee;
import parameta.soapdb.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public SaveEmployeeResponse saveEmployee(SaveEmployeeRequest request) {
        //ver que el usuario no exista
        //si existe lanzar una excepcion
        //guardar
        //devolver la respuesta

       if (employeeRepository.existsByIdNumber(request.getIdNumber())) {
           throw new EmployeeAlreadyExist("Employee already exists with ID: " + request.getIdNumber());
       }

        Employee employee = new Employee(
                request.getName(),
                request.getLastName(),
                request.getDocumentType(),
                request.getIdNumber(),
                request.getDateOfBirth().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                request.getDateOfJoiningCompany().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                request.getPosition(),
                request.getSalary()
        );

        employeeRepository.save(employee);

           // Crear la respuesta
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setMessage("Employee saved successfully");
        response.setSuccess(true);
        response.setEmployeeIdNumber(employee.getIdNumber());

        return response;
    }



}
