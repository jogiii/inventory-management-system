# Inventory Management System

A production-quality Inventory Management System built with Java 17, Spring Boot 3, and Clean Architecture.

## Quick Start
1.  **Run App**: `./gradlew bootRun`
2.  **Run Tests**: `./gradlew test`
3.  **Docs**: See [TESTING_GUIDE.md](TESTING_GUIDE.md) for detailed cURL commands and CI/CD setup.

## Architecture

The project follows a **Layered Architecture** (Controller -> Service -> Repository -> Database) with strict separation of concerns using DTOs at API boundaries.

### Design Patterns Used
1.  **Factory Method (Creational)**: Used to create `StockOperationHandler` instances.
2.  **Facade (Structural)**: `InventoryFacade` simplifies the interaction between Controllers and multiple services.
3.  **Strategy (Behavioral)**: `AlertStrategy` handles low-stock alerts differently.
4.  **Observer (Behavioral)**: `StockLevelMonitor` observes stock changes.
5.  **Command (Behavioral)**: `StockCommand` encapsulates stock modification requests.

## Project Structure
- `src/main/java/com/inventory/system`
    - `pattern`: Design pattern implementations.
    - `service`, `controller`, `repository`, `entity`: Standard layers.

## Technical Stack
- **Java 17**, **Spring Boot 3.2**
- **Data**: JPA, H2/PostgreSQL
- **Testing**: JUnit 5, Mockito, Testcontainers

## CI/CD & Quality
- **SonarQube**: Run `docker-compose up -d` then `./gradlew sonar`.
- **Jenkins**: `Jenkinsfile` included for automated pipeline.
