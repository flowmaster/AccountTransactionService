# Account based transaction service
Account Transaction API


This exposes two end point ,
Sending money between accounts  
Requesting account statement with account balance and list of transaction

assumption is here the customer is present else it throws a descriptive error response with code.

### Technology Stack -
- Java 8
- H2 ( embedded relational DB) - Taking this for code challenge purpose only  
- Spring boot 2
- Spring REST API
- Spring Data JPA
- Swagger API -  for easy documentation and demonstration
- Mockito ,JUnit
- Maven

### Prerequisite -

- Java 8.
- Maven.
All necessary api for tech stack

### Run application -
To run the application, below are the steps assuming the git client present on the system.

1. git clone https://github.com/flowmaster/AccountTransactionService.git
2. mvn clean install
3. java -jar target/AccountTransactionService-0.0.1-SNAPSHOT.jar

From Eclipse - TransactionServiceInitiation.java run (initiation class)

#### Swagger Access -
* http://localhost:8080/swagger-ui.html

### Application - 

#### Make a transfer operation - 

###### Endpoint - 
POST   /accounts/transfer

###### Interface - 

{
  "amount":100,
  "dstAccountId": "1",
  "srcAccountId": "2"
}

curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "amount":100,
  "dstAccountId": "1",
  "srcAccountId": "2"
}' 'http://localhost:8080/accounts/transfer'


#### Get the statement for an account - 

Endpoint - GET  /accounts/{accountId}/statement

curl -X GET --header 'Accept: application/json' 'http://localhost:8080/accounts/2/statement'



Due to time constraints and per assignment, creation of customer and accounts are not supported by this service, but the database is automatically initialized with records during the boot of the application with the predefined account and customer details.This we can view in the H2 database by below url
  http://localhost:8080/h2-console  .
