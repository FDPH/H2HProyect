package parameta.gateway.config;

import com.parameta.gateway.ws.EmployeesPort;
import com.parameta.gateway.ws.EmployeesPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapConfig {

    @Bean
    public EmployeesPortService employeesPortService() {
        // 1. Verifica que esta clase existe en target/generated-sources/jaxws/
        return new EmployeesPortService();
    }

    @Bean
    public EmployeesPort employeesPort() {
        return employeesPortService().getEmployeesPortSoap11();
    }
}
