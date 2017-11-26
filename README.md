This application serves Marvel character IDs (database IDs).

It alse serves the character details based on the database ID.

To run the application, cd into the marvel-character-service folder

and run:

mvn spring-boot:run

You can access the Swagger UI at :

http://localhost:8080/swagger-ui.html

and the Swagger specification at:

http://localhost:8080/v2/api-docs


SOAP Web Services:

Soap endpoints are published on : http://localhost:8080/services

e.g

http://localhost:8080/services/characters?wsdl

