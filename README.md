#  Wallet Application

A simple Spring Boot REST API for money transfers between users.

##  Features
* **User Management:** Create and list users.
* **Transfer:** Send money between accounts.
* **Security:** BCrypt password encryption.
* **Documentation:** Interactive API testing with Swagger.

##  Tech Stack
* Java 17, Spring Boot 3, PostgreSQL, Hibernate, Swagger.

##  Setup
1. Create a PostgreSQL database named `wallet_db`.
2. Configure your credentials in `src/main/resources/application-local.properties`.
3. Run: `./mvnw spring-boot:run`

##  API Endpoints
* **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
* **Users:** `POST /api/v1/users`
* **Transfer:** `POST /api/v1/wallets/transfer`
