package parameta.soapdb.exception;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapFaultDetailElement;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import parameta.soapdb.EmployeeFault;

import javax.xml.namespace.QName;

@Component
public class CustomSoapFaultExceptionResolver extends SoapFaultMappingExceptionResolver {


    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        if (ex instanceof EmployeeAlreadyExist) {

            SoapFaultDetail detail = fault.addFaultDetail();
            QName faultQName = new QName("http://parameta.com/soapdb/employee", "EmployeeFault");
            SoapFaultDetailElement detailElement = detail.addFaultDetailElement(faultQName);

            EmployeeFault errorDetail = new EmployeeFault();
            errorDetail.setErrorCode("EMPLOYEE_ALREADY_EXISTS");
            errorDetail.setMessage(ex.getMessage());
            errorDetail.setField("employeeId");


            // Usa el marshaller de Spring para convertir la clase en XML
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setClassesToBeBound(EmployeeFault.class);
            marshaller.marshal(errorDetail, detailElement.getResult());

        }
    }

}
