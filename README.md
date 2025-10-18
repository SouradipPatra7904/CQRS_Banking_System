# CQRS_Banking_System

**A CQRS + Event Sourcing banking system demonstrating enterprise-grade transaction processing with Spring Boot, Apache Kafka, Quartz Scheduler, and PostgreSQL.**

---

## 🚀 Project Vision

This project demonstrates a **high-performance banking system** built using **CQRS and Event Sourcing** principles:

* **Command Side:** Immutable transactions produce Kafka events.
* **Query Side:** Read models keep queries blazing fast.
* **Scheduled Jobs:** Quartz automates recurring tasks like interest accrual.
* **Relational Storage:** PostgreSQL stores both read models and Quartz job data.
* **REST API:** Provides endpoints for creating transactions and querying account summaries.

It serves as a compact showcase of **enterprise-grade transaction orchestration and event-driven architecture**.

---

## ⚙️ Tech Stack

* **Java 21**
* **Spring Boot 3.5.6**
* **Apache Kafka (KRaft mode)**
* **Quartz Scheduler**
* **PostgreSQL**
* **Maven (Jar packaging)**

---

## Running the Application

1. **Configure PostgreSQL in `application.properties`:**

```properties
server.port=9595
spring.datasource.url=jdbc:postgresql://localhost:5432/cqrs_bank
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

2. **Kafka Setup (KRaft mode):**

* Initialize Kafka (assuming your "Kafka" is installed in a directory named "kafka_local") :
    
```bash
KAFKA_CLUSTER_ID="$(kafka_local/bin/kafka-storage.sh random-uuid)"

~/kafka_local/bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c ~/kafka_local/config/server properties
```

   * Ensure Kafka broker is running on `localhost:9092` :

```bash
~/kafka_local/bin/kafka-server-start.sh ~/kafka_local/config/server.properties
```

   * Create a topic for transactions:

```bash
kafka-topics.sh --create --topic transactions --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

* Topic name is configurable via `application.properties`:

```properties
job.scheduler.kafka.topic=transactions
```

3. **Build & Run:**

```bash
mvn clean package
java -jar target/CQRS_Banking_System-0.0.1-SNAPSHOT.jar
```

4. **Server runs on:**

```
http://localhost:9595
```

---

## 🎯 Key REST Endpoints

### Command Side (Write)

| Endpoint                     | Method | Description                                |
| ---------------------------- | ------ | ------------------------------------------ |
| `/api/commands/transactions` | POST   | Create a new transaction (CREDIT or DEBIT) |

**Example Payload:**

```json
{
  "accountId": "acc123",
  "amount": 500.0,
  "type": "CREDIT"
}
```

---

### Query Side (Read)

| Endpoint                     | Method | Description                                |
| ---------------------------- | ------ | ------------------------------------------ |
| `/api/queries/accounts`      | GET    | Get all accounts with current balances     |
| `/api/queries/accounts/{id}` | GET    | Get a single account summary by account ID |

**Example Requests:**

```bash
# Get all accounts
curl http://localhost:9595/api/queries/accounts

# Get a single account
curl http://localhost:9595/api/queries/accounts/acc123
```

---

### Quartz Jobs (Scheduled)

* Quartz jobs run automatically according to cron schedules.
* Example: Daily interest accrual is executed by `InterestAccrualJob`.

Optional manual trigger endpoint (if implemented):

```http
POST /api/jobs/trigger-interest
```

---

## 🎯 Key Highlights

* **CQRS + Event Sourcing:** Commands produce Kafka events; queries use read-optimized models.
* **Immutable Transactions:** Every operation is recorded as an event in Kafka.
* **Quartz Scheduler:** Automates daily/weekly/monthly tasks.
* **PostgreSQL Storage:** Production-grade persistence for both read models and Quartz jobs.
* **REST API:** Full API for transaction commands and querying account summaries.
* **Clean architecture:** Modular packages (`config`, `command`, `query`, `scheduler`, `event`).

---

## 🏗️ Future Enhancements

* Add **audit logs** for all transactions.
* Implement **event replay** for rebuilding read models.
* Add **retry mechanism** and dead-letter topics for failed events.
* Integrate **Swagger/OpenAPI** for endpoint documentation.
* Add **metrics & observability** (Prometheus + Grafana) for monitoring events and jobs.
