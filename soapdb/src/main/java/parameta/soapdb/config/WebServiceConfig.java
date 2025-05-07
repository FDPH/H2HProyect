package parameta.soapdb.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "employees")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EmployeesPort"); // Nombre distinto al XSD
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://parameta.com/soapdb/employee");
        wsdl11Definition.setSchema(employeeSchema);

        // Configuración clave para evitar operaciones automáticas
        wsdl11Definition.setCreateSoap11Binding(true);
        wsdl11Definition.setRequestSuffix("Request");
        wsdl11Definition.setResponseSuffix("Response");

        // Fuerza solo las operaciones que quieres
        Properties soapActions = new Properties();
        soapActions.setProperty("SaveEmployee", "");
        wsdl11Definition.setSoapActions(soapActions);

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("wsdl/employee.xsd"));
    }

}
