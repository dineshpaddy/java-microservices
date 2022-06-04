# Sample application to manage transactions of customers
#######################<br />
    Technology stack <br/>
#######################
1. Spring Boot
2. Spring Data JPA
3. Spring Security
4. JWT
5. Swagger
6. Lombok
7. H2 In memory database
8. Maven
9. Junit
10. Mockito


#########################<br />
Notes<br />
#########################

1. The project is executable. It can be checked out and run.
2. There are 4 endpoints exposed as part of this sample project excluding the authentication endpoint.
3. Before we invoke any of the above mentioned 4 API's, we would have to invoke the "authenticate" API with username as "foo" and password as "foo" to get the JWT token. 

5. This JWT should be passed in the header as the Authorization key and the value as "Bearer <JWT token>".
   1. Example - "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2NTQzNTI3MTksImlhdCI6MTY1NDMxNjcxOX0.3GGYULDi50wwt75idHC0vrR3JDg9zypepXY83uvvuoA"
6. The swagger file can be found at http://localhost:8080/swagger-ui/
7. The h2 database can be viewed at http://localhost:8080/h2-console
8. The URL for the database would be : jdbc:h2:~/db/testdb
9. The username and password would be : "sa" and "password" respectively
10. The master data is pre populated in the database.


#########################<br />
    Sample Request<br />
#########################

1. Authenticate API

URL : http://localhost:8080/authenticate <br/>

Method : POST<br />
Header : <br/>
Content-Type:application/json

Request Body:<br/>
{
"username": "foo",
"password": "foo"
}

Response Body:<br/>
{
"jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2NTQzNTMzNzgsImlhdCI6MTY1NDMxNzM3OH0.PrK1ywUMsoRLuR9kYG-dbOQyzGRBwM5YLqMtjylQnZE"
}

2. Record API

URL : http://localhost:8080/v1/transactions/create <br/>

Method : POST<br />
Header : <br/>
Content-Type:application/json<br/>
Authorization: Bearer `<JWT Token>`

Request Body:<br/>
{
"customerId" : 10003,
"quantity" : 6,
"productCode" : "PRODUCT_001" ,
"transactionTime": "2022-06-03 19:56"
}

Response Body:<br/>
{<br/>
"message": "Request processed successfully.",<br/>
"code": "00000",<br/>
}

3. GET by Product

URL : http://localhost:8080/v1/transactions/product/PRODUCT_001<br />

Method : GET<br />
Header : <br/>
Content-Type:application/json<br/>
Authorization: Bearer `<JWT Token>`

Response Body:<br/>
{<br/>
"message": "Total cost of transactions per product",<br/>
"code": "00000",<br/>
"value": 300.0<br/>
}

4. GET by Customer

URL : http://localhost:8080/v1/transactions/customer/3<br />

Method : GET<br />
Header : <br/>
Content-Type:application/json<br/>
Authorization: Bearer `<JWT Token>`

Response Body:<br/>
{<br/>
"message": "Total cost of transactions per customer",<br/>
"code": "00000",<br/>
"value": 300.0<br/>
}

5. GET by Customer and Location

URL : http://localhost:8080/v1/transactions/customer/4/location/US<br />

Method : GET<br />
Header : <br/>
Content-Type:application/json<br/>
Authorization: Bearer `<JWT Token>`

Response Body:<br/>
{<br/>
"message": "Number of transactions sold to customer from Australia",<br/>
"code": "00000",<br/>
"value": 6<br/>
}<br/>