# Sharded Saga Wallet

A Spring Boot application demonstrating a distributed wallet system using sharding and saga pattern for transaction management.

## Overview

This project implements a wallet system that uses:
- **Sharding**: Database sharding for horizontal scalability using Apache ShardingSphere
- **Saga Pattern**: Distributed transaction management for maintaining data consistency across shards (planned)

The application currently implements user management with automatic database sharding based on user ID, distributing data across multiple PostgreSQL databases.

## Technology Stack

- **Java 21**: Modern Java features and performance improvements
- **Spring Boot 4.0.0**: Application framework
- **Spring Data JPA**: Data persistence layer
- **Spring Web MVC**: REST API endpoints
- **Apache ShardingSphere 5.5.2**: Database sharding framework
- **PostgreSQL**: Relational database
- **Lombok**: Reduces boilerplate code
- **Gradle**: Build automation tool

## Prerequisites

- Java 21 or higher
- PostgreSQL database server
- Gradle (or use the included Gradle wrapper)

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd sharded-saga-wallet
   ```

2. **Create PostgreSQL databases**
   - Create two databases for sharding:
     ```sql
     CREATE DATABASE shardwallet1;
     CREATE DATABASE shardwallet2;
     ```

3. **Configure database credentials**
   - Update `src/main/resources/sharding.yaml` with your PostgreSQL credentials:
     ```yaml
     dataSources:
       shardwallet1:
         jdbcUrl: jdbc:postgresql://localhost:5432/shardwallet1
         username: your_username
         password: your_password
       shardwallet2:
         jdbcUrl: jdbc:postgresql://localhost:5432/shardwallet2
         username: your_username
         password: your_password
     ```

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run the application**
   ```bash
   ./gradlew bootRun
   ```
   
   Or run the JAR file:
   ```bash
   java -jar build/libs/sharded-saga-wallet-0.0.1-SNAPSHOT.jar
   ```

   The application will start on `http://localhost:8080` (default Spring Boot port).

## Sharding Configuration

The application uses Apache ShardingSphere to automatically shard the `users` table across two databases:
- **Sharding Strategy**: Based on user `id` column
- **Sharding Algorithm**: `id % 2 + 1` (distributes users between `shardwallet1` and `shardwallet2`)
- **Key Generation**: Snowflake algorithm for generating unique IDs

Configuration is defined in `src/main/resources/sharding.yaml`.

## API Endpoints

### Create User
```http
POST /users/create
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Response:**
```json
{
  "id": 1234567890123456789,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### Get User by ID
```http
GET /users/{id}
```

**Response:**
```json
{
  "id": 1234567890123456789,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/sharded_saga_wallet/
│   │   ├── ShardedSagaWalletApplication.java
│   │   ├── controller/
│   │   │   └── UserController.java          # REST API endpoints
│   │   ├── entities/
│   │   │   └── User.java                    # User entity
│   │   ├── repositories/
│   │   │   └── UserRepository.java          # JPA repository
│   │   └── services/
│   │       └── UserService.java            # Business logic
│   └── resources/
│       ├── application.properties           # Spring Boot configuration
│       └── sharding.yaml                    # ShardingSphere configuration
└── test/
    └── java/com/example/sharded_saga_wallet/
        └── ShardedSagaWalletApplicationTests.java
```

## Development

### Running Tests
```bash
./gradlew test
```

### Building the Project
```bash
./gradlew build
```

### Cleaning the Build
```bash
./gradlew clean
```

## Features

- ✅ Spring Boot application with JPA support
- ✅ PostgreSQL database integration with sharding
- ✅ Apache ShardingSphere integration for automatic sharding
- ✅ REST API endpoints for user management
- ✅ Automatic database routing based on user ID
- ✅ Snowflake ID generation for distributed systems
- ✅ Development tools for hot reloading
- ✅ Test framework setup

## Future Enhancements

- Add saga orchestration for distributed transactions
- Implement wallet operations (deposit, withdraw, transfer)
- Add transaction management and consistency guarantees
- Implement cross-shard transaction handling
- Add comprehensive error handling and validation
- Add API documentation (OpenAPI/Swagger)

## License

This is a demo project for educational purposes.

