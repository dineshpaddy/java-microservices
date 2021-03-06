# Sample application to expose two GET endpoints
#######################<br />
    Technology used<br />
#######################
1. Spring Boot
2. Spring Data JPA
3. Swagger
4. Lombok
5. H2 In memory database
6. Maven
7. Junit
8. Mockito


#########################<br />
Notes<br />
#########################

1. The project is executable. It can be checked out and run.
2. There are two endpoints exposed at part of this sample.
3. Both the endpoints are GET endpoints. 
4. The swagger file can be found at http://localhost:8080/swagger-ui/
5. The h2 database can be viewed at http://localhost:8080/h2-console
6. The URL for the database would be : jdbc:h2:~/db/testdb
7. The username and password would be : "sa" and "password" respectively
8. Pagination is implemented for both the endpoints with the default perPage size set to 5
9. Validation is enabled to not use perPage more than the default value of 5.
10. transactionID in header is mandatory attribute for both the requests.


#########################<br />
    Sample Request<br />
#########################

1. GET accounts by UserId

URL without pagination : http://localhost:8080/v1/account/user/2<br />
URL with pagination : http://localhost:8080/v1/account/user/2?page=1&size=2

Method : GET<br />
Header :
  transactionID:85f46f54-e05b-430f-8caa-9034a0a641de
  
2. GET transactions by AccountId

URL without pagination : http://localhost:8080/v1/account/2/transactions<br />
URL with pagination : http://localhost:8080/v1/account/2/transactions?page=1&size=2

Method : GET<br />
Header :
  transactionID:85f46f54-e05b-430f-8caa-9034a0a641dd


