package parameta.soapdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parameta.soapdb.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByIdNumber(String idNumber);
}
