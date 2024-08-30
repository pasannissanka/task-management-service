# Task Management Service

## Description

This is a task management service that allows users to create, read, update, and delete tasks. The service is built using the Spring Boot framework and uses a MySQL database to store tasks. The service provides a RESTful API that allows users to interact with the service.

## Requirements

- Java 21
- Maven
- MySQL
- Docker

## Installation

1. Clone the repository
2. Run the following command to build the project:
    ```
    mvn clean package -U
    ```
3. Run using docker-compose:
    ```
    docker-compose build && docker-compose up
    ```
   (Note: This will start the MySQL database and the task management service) A MySQL Database is embedded in the docker-compose file. The database will be available at `http://localhost:3308`

4. The service will be available at `http://localhost:8081`

## Running the tests

To run the tests, run the following command:
```
mvn test
```

## Project Structure

The project is structured as maven multi-module project. The project has the following modules

- beans: Contains the db entity classes, Dtos and exception classes
- core: Contains the core classes, including the repository, and services
- web: Contains the web server, including the controllers and the configuration classes

Each module has its own `pom.xml` file and can be built separately.

## API Documentation

The API documentation is available at `http://localhost:8081/swagger-ui/index.html`


