# plt-device-management

- Author: Diego Marinho Almeida
- Since: Dez/2024
- Version: 1.0

## Stack
- Java 17
- Maven (3.6.3 ou posterior)
- Spring Boot 3.1.4
- Base de dados H2
- Lombok
- Swagger

# Device Management Service

This is a RESTful service implemented in Java 17 using Spring Boot. The service allows managing a database of devices with CRUD operations and search functionality.

## Features

### Entities
- **Device**: Represents a device with the following fields:
  - `id` (Long): The unique identifier for the device.
  - `name` (String): The name of the device.
  - `brand` (String): The brand of the device.
  - `creationTime` (LocalDateTime): The timestamp of when the device was created.

### Operations
1. **Add Device**: Add a new device to the database.
2. **Get Device by ID**: Retrieve a specific device by its unique identifier.
3. **List All Devices**: Retrieve all devices stored in the database.
4. **Update Device**: Update a device with full or partial details.
5. **Delete Device**: Remove a device by its identifier.
6. **Search Devices by Brand**: Search for devices by their brand.

## Endpoints

### Base URL
`/devices`

### API Endpoints
| HTTP Method | Endpoint              | Description                              |
|-------------|-----------------------|------------------------------------------|
| `POST`      | `/devices`            | Add a new device.                       |
| `GET`       | `/devices/{id}`       | Retrieve a device by ID.                |
| `GET`       | `/devices`            | List all devices.                       |
| `PUT`       | `/devices/{id}`       | Update a device (full update).          |
| `PATCH`     | `/devices/{id}`       | Partially update a device.              |
| `DELETE`    | `/devices/{id}`       | Delete a device by ID.                  |
| `GET`       | `/devices/search`     | Search devices by brand using a query parameter. |

### Swagger UI
For easy exploration and testing of the API, you can access the Swagger UI documentation at the following URL:

[Swagger UI Documentation](http://localhost:8080/swagger-ui/index.html#/)

This interface provides an interactive way to test the API endpoints directly from your browser.

## Technology Stack
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate Validator**
- **H2 (in-memory) database** (for testing)

## Setup

### Prerequisites
- JDK 17 or higher
- Maven 3.8 or higher

### Steps to Run the Application in MAVEN
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run`.
4. The service will be available at `http://localhost:8080/devices`.
5. You can access the Swagger UI at `http://localhost:8080/swagger-ui/index.html#/`.

---

### Build and Run the Application in Docker

### Steps to Build and Run the Application in Docker

1. Clone the repository.
2. Navigate to the project directory.
3. USe the `Dockerfile` in the root of the project.
4. Build the Docker image by running:

    ```bash
    docker build -t device-management .
    ```

5. Run the Docker container using:

    ```bash
    docker run -p 8080:8080 device-management
    ```

6. You can access the Swagger UI at `http://localhost:8080/swagger-ui/index.html#/`.

---

### Example Requests

#### Add a Device
```bash
POST /devices
Content-Type: application/json

{
  "name": "Smartphone",
  "brand": "TechBrand"
}
```

#### Search by Brand
``` bash
GET /devices/search?brand=TechBrand
```

## Contributions
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.