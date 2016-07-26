## Test Application

REST HTTPS API

Using:

	- Spring Boot
	- MySQL via Spring Data JPA and Hibernate
	- Spring Security
	- JUnit
	- AngularJS

### Build and run

#### Configurations

Open the application.properties file and set your own configurations (database settings).

#### From Eclipse (Spring Tool Suite)

Import as Existing Maven Project and run it as Spring Boot App.

### Usage

Run the application and go on:
 
	https://localhost:9000/

Test User account:
 
	- login: user 
	- password: user

API provides following methods:

	- GET /objects - returns full objects list
	- GET /object/<id> - retrieve the object with the passed id
	- POST/PUT /object/id - saves the object with the given id (if <id> is not specified a new one is created, new object with id returns in the response body)
	- DELETE /object/id - removes object
