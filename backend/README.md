# Transport Management System (TMS) Backend

## Overview
This project is a fully functional backend for a Transport Management System (TMS) built with **Spring Boot 4.0**, **Java 17**, and **PostgreSQL**. It is designed to manage loads, transporters, bids, and bookings for a logistics platform. The backend follows best practices for RESTful API design, uses JPA for data persistence, and integrates Swagger/OpenAPI for API documentation.

---

## Features
- **Load Management**: Create, update, and track loads to be shipped.
- **Transporter Management**: Register and manage transporters and their trucks.
- **Bid System**: Transporters can bid on loads; shippers can review and accept bids.
- **Booking System**: Confirmed bids become bookings, tracking the transport process.
- **Status Tracking**: Loads, bids, and bookings have status enums for workflow management.
- **Exception Handling**: Custom exceptions and a global handler for robust error responses.
- **Pagination, Filtering, Sorting**: All list endpoints support these features.
- **Swagger/OpenAPI**: Interactive API documentation at `/swagger-ui.html`.

---

## Project Structure
```
backend/
├── src/main/java/com/ManasRanjanDikshit/tms/backend/
│   ├── entity/         # JPA entities (Load, Transporter, Bid, Booking, enums)
│   ├── repository/     # Spring Data JPA repositories
│   ├── dto/            # Data Transfer Objects for requests/responses
│   ├── exception/      # Custom exceptions and global handler
│   ├── service/        # Business logic for each domain
│   ├── controller/     # REST API controllers
│   └── config/         # Configuration (Swagger/OpenAPI)
├── src/main/resources/
│   ├── application.properties # Database and app config
│   ├── static/         # Static resources (if any)
│   └── templates/      # Templates (if any)
├── pom.xml             # Maven build file
└── README.md           # Project documentation
```

---

## Detailed Explanation

### 1. Entity Package (`entity/`)
- **Load.java**: Represents a shipment. Fields include origin, destination, weight, status, etc.
- **Transporter.java**: Represents a transporter company. Includes company info and truck details.
- **Bid.java**: Represents a bid by a transporter on a load. Includes price, status, references to load and transporter.
- **Booking.java**: Represents a confirmed transport job. Links a bid, load, and transporter.
- **Enums**: `LoadStatus`, `BidStatus`, `BookingStatus`, `WeightUnit` define allowed states and units.

### 2. Repository Package (`repository/`)
- **LoadRepository.java**: CRUD and custom queries for loads.
- **TransporterRepository.java**: CRUD and custom queries for transporters.
- **BidRepository.java**: CRUD and custom queries for bids.
- **BookingRepository.java**: CRUD and custom queries for bookings.

### 3. DTO Package (`dto/`)
- **Request DTOs**: Used for creating/updating entities via API (e.g., `LoadRequestDTO`).
- **Response DTOs**: Used for sending entity data to clients (e.g., `LoadResponseDTO`).

### 4. Exception Package (`exception/`)
- **Custom Exceptions**: Handle business rule violations (e.g., `InvalidStatusTransitionException`, `InsufficientCapacityException`).
- **GlobalExceptionHandler.java**: Catches exceptions and returns meaningful error responses.

### 5. Service Package (`service/`)
- **LoadService.java**: Business logic for loads (create, update, status changes).
- **TransporterService.java**: Business logic for transporters (register, manage trucks).
- **BidService.java**: Business logic for bids (place, update, cancel).
- **BookingService.java**: Business logic for bookings (confirm, track).

### 6. Controller Package (`controller/`)
- **LoadController.java**: REST endpoints for load operations.
- **TransporterController.java**: REST endpoints for transporter operations.
- **BidController.java**: REST endpoints for bid operations.
- **BookingController.java**: REST endpoints for booking operations.

### 7. Config Package (`config/`)
- **SwaggerConfig.java**: Configures Swagger/OpenAPI for interactive API docs.

### 8. Resources (`src/main/resources/`)
- **application.properties**: Database connection, JPA, and other app settings.

---

## How to Run
1. **Install Java 17 and PostgreSQL**.
2. **Configure `application.properties`** with your database credentials.
3. **Build the project**:
   ```sh
   mvn clean install
   ```
4. **Run the application**:
   ```sh
   mvn spring-boot:run
   ```
5. **Access API docs**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## API Documentation
- All endpoints are documented and testable via Swagger UI.
- Common endpoints:
  - `/loads` - Manage loads
  - `/transporters` - Manage transporters
  - `/bids` - Place and manage bids
  - `/bookings` - Manage bookings

---

## For Beginners
- **Entities** are Java classes mapped to database tables.
- **Repositories** handle database operations.
- **DTOs** are used to transfer data between client and server.
- **Services** contain business logic, not direct database or HTTP code.
- **Controllers** expose REST endpoints for clients (like frontend or mobile apps).
- **Exceptions** help handle errors gracefully and return clear messages.
- **Swagger/OpenAPI** provides a UI to explore and test the API.

---

## Contribution & Customization
- Follow Java naming conventions (package names should be lowercase).
- Add new features by creating new entities, services, controllers, and updating the API docs.
- Use DTOs to avoid exposing internal entity details directly.
- Write tests in `src/test/java` to ensure code quality.

---

## License
This project is open-source and free to use for educational and commercial purposes.

---

## Contact
For questions or support, contact the maintainer at: [your-email@example.com]

---

## Quick Reference Table
| Package         | Purpose                                      |
|----------------|----------------------------------------------|
| entity         | JPA entities and enums                       |
| repository     | Data access layer (Spring Data JPA)          |
| dto            | Data Transfer Objects (API input/output)      |
| exception      | Custom exceptions and global error handler    |
| service        | Business logic for each domain               |
| controller     | REST API endpoints                           |
| config         | Configuration (Swagger/OpenAPI)              |
| resources      | App config, static files, templates          |

---

Happy coding! 🚚📦
