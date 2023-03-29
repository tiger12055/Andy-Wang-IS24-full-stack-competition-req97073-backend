# IMB Product Tracker System

This is a Spring Boot backend project that serves as a RESTful API for a full-stack application for IMB job application.

## Project Description

This project is built using Spring Boot(version 3.05), and provides API endpoints for managing products and developers in a product management system. It supports CRUD operations and search functionality for products by Scrum Master and developer names.

## Prerequisites

- Java Development Kit (JDK) 11 or later(I am using Jave 17)
- Maven (optional, the project includes a Maven Wrapper)

## Running the Application

To run the application, follow these steps:

1. Open a terminal and navigate to the project's root directory (the same level where `pom.xml` is located).

2. Run the following command(the default port is 3000):

   For Unix-based systems:
   ```
   ./mvnw spring-boot:run
   ```
   or
   ```
   ./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=PORT_NUMBER"
   ```

   For Windows:
   ```
   mvnw.cmd spring-boot:run
   ```
   or
   ```
   mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=PORT_NUMBER"
   ```

If you have Maven installed on your system, you can use:
    ```
    mvn spring-boot:run
    ```
    or
    ```
    mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=PORT_NUMBER"
    ```

3. Once the application starts successfully, you should see log messages indicating that the application is running, including the port number it's listening on (usually 8080 by default).

4. You can now access the API endpoints using an API client, such as Postman, or by connecting the backend to a frontend application.

## API Endpoints
- `GET /api/products`: Fetch all products
- `POST /api/products`: Create a new product
- `GET /api/products/{productNumber}`: Fetch a single product by product number
- `PUT /api/products/{productNumber}`: Update a product by product number（my assumption: the dev must be in my database then user can add this specific developer）
- `GET /api/products/search/scrum-master?name={name}`: Search products by Scrum Master name
- `GET /api/products/search/developer?name={name}`: Search products by developer name