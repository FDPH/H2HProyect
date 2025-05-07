package parameta.gateway.config;

import com.parameta.gateway.ws.EmployeesPort;
import com.parameta.gateway.ws.EmployeesPortService;
import jakarta.xml.ws.BindingProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SoapConfig {

    @Value("${soap.client.default-uri}") // Valor por defecto si no está configurado
    private String soapEndpoint;

    @Bean
    public EmployeesPortService employeesPortService() {
        // 1. Verifica que esta clase existe en target/generated-sources/jaxws/

        return new EmployeesPortService();
    }

    @Bean
    public EmployeesPort employeesPort() {
        EmployeesPort port = employeesPortService().getEmployeesPortSoap11();
        log.info("SOAP Endpoint: {}", soapEndpoint);
        // Configura el endpoint en el BindingProvider
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                soapEndpoint
        );

        return port;
    }
}
