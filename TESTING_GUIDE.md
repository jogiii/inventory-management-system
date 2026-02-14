# Testing & Running Guide

## 0. Prerequisites (Action Required)
It appears clearly that **Java**, **Gradle**, or **Maven** are not currently in your system's PATH.
To run this application, you **MUST** have the following installed and configured:
1.  **JDK 17+**: [Download Here](https://adoptium.net/)
2.  **Gradle** (if using Gradle): [Install Guide](https://gradle.org/install/)

**Since the `gradlew` (Wrapper) scripts are not yet generated (requires Gradle to generate), you must run commands using an installed `gradle` instance or open the project in an IDE like IntelliJ IDEA which will handle this for you.**

---

## 1. How to Run the Application
Once you have Gradle installed and in your PATH:

```bash
# Calculate absolute path if needed or just:
gradle bootRun
```
*Note: Use `gradle` instead of `./gradlew` until you generate the wrapper with `gradle wrapper`.*

The application will start on port `8080`.
H2 Database Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:inventorydb`)

---

## 2. Testing Endpoints (Manual)

Here are the `cURL` commands to test the full flow.

### Step 1: Create a Category
```bash
curl -X POST http://localhost:8080/api/categories \
-H "Content-Type: application/json" \
-d '{
  "name": "Electronics",
  "description": "Gadgets and devices"
}'
```

### Step 2: Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{
  "name": "Gaming Laptop",
  "sku": "GL-9000",
  "description": "High performance laptop",
  "price": 2500.00,
  "lowStockThreshold": 5,
  "categoryId": 1
}'
```

### Step 3: Stock IN Operation
```bash
curl -X POST http://localhost:8080/api/inventory/operate \
-H "Content-Type: application/json" \
-d '{
  "productId": 1,
  "warehouseId": 1,
  "quantity": 20,
  "type": "IN",
  "reason": "Initial Purchase"
}'
```

### Step 4: Stock OUT Operation (Simulating Sale)
```bash
curl -X POST http://localhost:8080/api/inventory/operate \
-H "Content-Type: application/json" \
-d '{
  "productId": 1,
  "warehouseId": 1,
  "quantity": 2,
  "type": "OUT",
  "reason": "Customer Order #123"
}'
```

---

## 3. Running Automated Tests

### Unit & Integration Tests
Run all tests:
```bash
gradle test
```
View reports at: `build/reports/tests/test/index.html`

---

## 4. Code Quality & SonarQube

### Step 1: Start SonarQube Server
Use the provided `docker-compose.yml` (Requires Docker):
```bash
docker-compose up -d
```
Access SonarQube at `http://localhost:9000` (Default login: `admin` / `admin`).

### Step 2: Run Analysis
```bash
gradle sonar
```

---

## 5. CI/CD Pipeline
The `Jenkinsfile` in the root directory defines the pipeline.
1.  **Install Jenkins**.
2.  **Create New Pipeline Job**.
3.  **Point to Git Repository**.
4.  **Build**.

The pipeline will:
1.  Checkout Code.
2.  Build & Test.
3.  Run Sonar Analysis.
4.  Check Quality Gate.
