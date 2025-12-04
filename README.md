# Sharded Saga Wallet

A Spring Boot application demonstrating a distributed wallet system using sharding and saga pattern for transaction management.

## Overview

This project implements a wallet system that uses:
- **Sharding**: Database sharding for horizontal scalability
- **Saga Pattern**: Distributed transaction management for maintaining data consistency across shards

## Technology Stack

- **Java 21**: Modern Java features and performance improvements
- **Spring Boot 4.0.0**: Application framework
- **Spring Data JPA**: Data persistence layer
- **PostgreSQL**: Relational database
- **Lombok**: Reduces boilerplate code
- **Gradle**: Build automation tool

## Prerequisites

- Java 21 or higher
- PostgreSQL database
- Gradle (or use the included Gradle wrapper)

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd sharded-saga-wallet
   ```

2. **Configure PostgreSQL**
   - Create a PostgreSQL database
   - Update `src/main/resources/application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run the application**
   ```bash
   ./gradlew bootRun
   ```
   
   Or run the JAR file:
   ```bash
   java -jar build/libs/sharded-saga-wallet-0.0.1-SNAPSHOT.jar
   ```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/sharded_saga_wallet/
│   │   └── ShardedSagaWalletApplication.java
│   └── resources/
│       └── application.properties
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

- Spring Boot application with JPA support
- PostgreSQL database integration
- Development tools for hot reloading
- Test framework setup

## Future Enhancements

- Implement wallet sharding logic
- Add saga orchestration for distributed transactions
- Create REST API endpoints for wallet operations
- Add transaction management and consistency guarantees

## License

This is a demo project for educational purposes.

