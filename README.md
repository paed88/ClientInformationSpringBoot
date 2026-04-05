# 🚀 Client Information Management System (CIMS)

A professional-grade RESTful API built with **Spring Boot 3**, designed for secure and scalable client data management. This project was developed as a technical demonstration of modern Java backend architecture.

## 🛠 Tech Stack & Architecture
* **Framework:** Spring Boot 3.x (Java 17)
* **Security:** Stateless JWT (JSON Web Token) Authentication
* **Database:** Microsoft SQL Server (MSSQL)
* **ORM:** Spring Data JPA / Hibernate
* **Documentation:** OpenAPI 3.0 (Swagger UI)
* **Logging:** Custom AOP (Aspect-Oriented Programming) for business activity tracking
* **Validation:** Jakarta Bean Validation (Fail-Fast principle)

## ✨ Key Features
* **Secure Auth:** Login endpoint to generate JWT Bearer tokens.
* **Full CRUD:** Complete management of user profiles with automated audit fields (`updatedAt`, `updatedBy`).
* **Advanced Mapping:** Partial updates via `PATCH` requests to minimize data transfer.
* **Smart Pagination:** Efficient data retrieval using Spring Data `Pageable`.
* **Observability:** All business-critical methods are intercepted by an AOP Aspect and logged to `logs/application.log`.
* **External Integration:** Consumes 3rd-party APIs using `RestTemplate` for data enrichment.

## 🚀 Getting Started

### Prerequisites
* JDK 17 or higher
* Maven 3.x
* MSSQL Server instance

### Postman Collection JSON file
* **File Path:** src/main/resources/User_Information_API.postman_collection.json

### TESTDB Table Query
* **File Path:** src/main/resources/schema.sql

### Configuration
Update the `src/main/resources/application.properties` with your database credentials:
```properties
spring.application.name=BankingCardClient

# MSSQL Connection
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=TESTDB;encrypt=true;trustServerCertificate=true
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA / Hibernate Properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

# LOG
logging.file.name=logs/application.log
logging.level.com.example.clientsystem.client=INFO
