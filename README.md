# Insurance Policy Management Application

## Prerequisites

- Docker: Ensure Docker is installed on your machine.
- Docker Compose: Ensure Docker Compose is installed.
- Maven: Ensure Maven is installed on your machine.

## Steps to Run the Application

1. **Start the MySQL Container**

   First, start the MySQL container to ensure the database is available:

   ```sh
   docker-compose up -d mysql-container
   ```

2. **Build the Application**

    Once the MySQL container is running, build the Spring Boot application using Maven:
   
    ```sh
    mvn clean package
    ```

3. **Start the app container**
   Use Docker Compose to build and run the application and ensure all containers are running:

    ```sh
    docker-compose up --build
    ```


API Endpoints: The API will be available at http://localhost:8080.
API Documentation: Access the API documentation at http://localhost:8080/swagger-ui.html.