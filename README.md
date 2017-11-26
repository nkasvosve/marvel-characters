If you are running the application from an IDE, you should add environment variable GOOGLE_API_KEY to the IDE
of choice and set it to the Google API Key.

If you are running on the command line, you need to export the
Google API Key as follows

export GOOGLE_API_KEY=XYZ

where XYZ is the key. This export is only needed if you need to call the

/characters/{id}/powers?languagCode=XYZ endpoint

To run the application, cd into the marvel-character-service folder

and run:

mvn spring-boot:run

You can access the Swagger UI at :

http://localhost:8080/swagger-ui.html

and the Swagger specification at:

http://localhost:8080/v2/api-docs


FYI: You need and internet connection


SOAP Web Services:

Soap endpoints are published on : http://localhost:8080/services

e.g

http://localhost:8080/services/characters?wsdl


export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home

