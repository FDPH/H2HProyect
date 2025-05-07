Hecho por **Freth David Piraban Hernandez**.

## Descripción
Se creo un modulo Rest (gateway) para la API, el cual se encarga de recibir las peticiones y redirigirlas al soap
Se creo un modulo soap (soapdb) que es el encargada de recibir las peticiones y almacenar en base de datos

## Base de datos
Base de datos en mysql, solo se genero una tabla para no extender el proyecto

url:
rest: localhost:8080/api/employee
soap: http://localhost:8081/ws/employees.wsdl

## Adicional
Se crea un swagger para la API Rest, el cual permite ver el endpoint y probarlos
