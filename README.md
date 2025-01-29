## Microservice for Teachers Management.

### Description
This system uses a feign client to map Instructors to Teachers. Made using a variation of Hexagonal architecture. <br>
This is the producer.<br>
That is the <a href="https://github.com/guigann/ms-teachers-management-consumer" target="_blank"> consumer</a>. <br>

### [Documentation](docs/MS%20Teachers%20Management%20V1.pdf)

### How to Run
1. Clone the repository

```
git clone https://github.com/guigann/ms-teachers-management.git
```
2. Make sure that maven is installed in the project and that the .jar files are generated in the **/target** folder, otherwise, perform the step below:
```
mvn clean
mvn install
mvn package
```
3. Run the docker-compose for Kafka
```
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up
```
> **Note:** Make sure to run the above commands in the **project root directory**.
4. Mock the Instructors Service with SoapUI. Follow the steps bellow:
   1. Create the Mock Service like this ([ProviderMockService.xml](ProviderMockService.xml)):
      <br /> ![img](https://github.com/user-attachments/assets/29b26efb-01fe-4910-8c58-802b84253f1a)<br />
   2. Provide valids responses to all endpoints according to Swagger.

5. Finally, run the project and enjoy :)
## Swaggers
[SwaggerMSTeacherManagement.yaml](swaggers%2FSwaggerMSTeacherManagement.yaml)

[SwaggerInstructorManagementAPI.yaml](swaggers%2FSwaggerInstructorManagementAPI.yaml)
