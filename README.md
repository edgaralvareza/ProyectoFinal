# RestIpFraudes
 Proyecto final para academia de IBM desarrollado con Java 11 en IntelliJ IDEA Comunity Edition
 
El proyecto está compuesto por los siguientes elementos:
POO
Spring Boot Web
Spring Boot
Eureka Server
Api Gateway Spring Cloud
Puerto dinámico para el api principal
Resilience4J con un método alternativo
Consumo de api por RestTemplate
H2 Base de datos en memoria
JPA / Hibernate
Logs
Patrón DTO
Métodos encapsulados
# Endpoints
Los endpoints para consultar las funciones de la api principal se describen a continuación:

Consultar información completa de una IP y obtener datos de su moneda: "URL"+ IP que se desea consultar
http://localhost:8090/api-gateway/api/v1/rest-ip/ip/consultarinfo/ip/

Para hacer una consulta y se retorne una excepción por IP baneada, se debe usar una IP almacenada en la base de datos. Las IP baneadas se ingresan con un archivo import.sql al arrancar RestIP, para probar la función se recomienda usar:
http://localhost:8090/api-gateway/api/v1/rest-ip/ip/consultarinfo/ip/187.189.221.61
o
http://localhost:8090/api-gateway/api/v1/rest-ip/ip/consultarinfo/ip/127.127.127.127

# Comentarios adicionales
-Al ejecutar coon debug "RestIp" se puede desplegar un warning/error pero no influye en el funcionamiento del programa, únicamente se debe omitir el breakpoint y dejar que el programa se ejecute hasta el final. 
