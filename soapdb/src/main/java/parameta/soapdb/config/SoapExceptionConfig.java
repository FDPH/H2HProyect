package parameta.soapdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import parameta.soapdb.exception.CustomSoapFaultExceptionResolver;

@Configuration
public class SoapExceptionConfig {

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver() {
        CustomSoapFaultExceptionResolver resolver = new CustomSoapFaultExceptionResolver();
        resolver.setOrder(1);

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.CLIENT);
        resolver.setDefaultFault(faultDefinition);

        return resolver;
    }
}