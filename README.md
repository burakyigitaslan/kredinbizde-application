# Kredinbizde Application

A microservices-based loan aggregation platform that connects users with multiple banks (Akbank, Garanti) for loan applications, credit cards, and campaigns. Built with event-driven architecture for scalability and resilience.

## Architecture Overview

```
                        ┌──────────────────┐
                        │   API Gateway    │
                        │     (:8084)      │
                        └───────┬──────────┘
                                │
                   ┌────────────┼────────────┐
                   │            │            │
          ┌────────▼──┐  ┌─────▼──────┐  ┌──▼───────────┐
          │  Akbank   │  │ Kredinbizde│  │   Garanti    │
          │  Service  │  │  Service   │  │   Service    │
          │  (:8081)  │  │  (:8080)   │  │   (:8083)    │
          └───────────┘  └──┬───┬─────┘  └──────────────┘
                            │   │
                 ┌──────────┘   └──────────┐
                 │                         │
          ┌──────▼──────┐          ┌───────▼──────┐
          │  RabbitMQ   │          │    Kafka     │
          │  (:5672)    │          │   (:9092)    │
          └──────┬──────┘          └──────────────┘
                 │
       ┌─────────▼──────────┐
       │ Notification Svc   │
       │     (:8082)        │
       └────────────────────┘

       All services register with Eureka Discovery (:8761)
       Kredinbizde Service uses Redis (:6379) for caching
```

## Key Features

- **Loan Aggregation** — Compare and apply for loans across multiple bank partners (Akbank, Garanti)
- **Credit Card Management** — Full CRUD operations for credit card offerings with bank-based filtering
- **Campaign Engine** — Bank-specific campaigns with credit card association and filtering
- **Multi-Channel Notifications** — Email, SMS, and mobile push via Strategy pattern
- **Event-Driven Messaging** — RabbitMQ for notifications, Kafka for application event streaming
- **Service Discovery** — Eureka-based dynamic service registration
- **API Gateway** — Centralized routing and entry point
- **Caching** — Redis caching for high-read operations
- **Global Exception Handling** — Centralized error handling with custom exceptions
- **Auditing** — Base entity auditing for tracking creation/modification timestamps
- **Unit Tested** — Service-layer tests for all 7 core domains (JUnit 5 + Mockito)

## Tech Stack

| Layer            | Technology                                |
| :--------------- | :---------------------------------------- |
| Language         | Java 17, Spring Boot 3                    |
| Cloud            | Spring Cloud (Gateway, Eureka, OpenFeign) |
| Database         | PostgreSQL (database-per-service)         |
| Messaging        | Apache Kafka, RabbitMQ                    |
| Caching          | Redis                                     |
| Containerization | Docker, Docker Compose                    |
| API Docs         | Swagger / OpenAPI                         |
| Testing          | JUnit 5, Mockito                          |

## Services

| Service                  | Port   | Description                                                        | Docs                                                          |
| :----------------------- | :----- | :----------------------------------------------------------------- | :------------------------------------------------------------ |
| **API Gateway**          | `8084` | Routes all external requests to downstream services                | —                                                             |
| **Kredinbizde Service**  | `8080` | Core platform — users, applications, loans, campaigns, credit cards | [Swagger](http://localhost:8080/swagger-ui/index.html)        |
| **Akbank Service**       | `8081` | Akbank loan product integration                                    | [Swagger](http://localhost:8081/swagger-ui/index.html)        |
| **Notification Service** | `8082` | Consumes RabbitMQ messages and dispatches notifications            | —                                                             |
| **Garanti Service**      | `8083` | Garanti loan product integration                                   | [Swagger](http://localhost:8083/swagger-ui/index.html)        |
| **Discovery Server**     | `8761` | Eureka service registry                                            | [Dashboard](http://localhost:8761)                            |

## Domain Model (Kredinbizde Service)

`User` · `Application` · `Bank` · `Loan` · `CreditCard` · `Campaign` · `Address` · `Log` · `Audit` · `Product`

## API Endpoints

### Users (`/api/users`)
| Method | Endpoint              | Description        |
| :----- | :-------------------- | :----------------- |
| POST   | `/api/users`          | Create a new user  |
| GET    | `/api/users`          | List all users     |
| GET    | `/api/users/{id}`     | Get user by ID     |
| GET    | `/api/users/{email}`  | Get user by email  |
| PUT    | `/api/users/{email}`  | Update user        |
| DELETE | `/api/users/{id}`     | Delete user        |

### Applications (`/api/applications`)
| Method | Endpoint                          | Description                      |
| :----- | :-------------------------------- | :------------------------------- |
| POST   | `/api/applications`               | Create a loan application        |
| POST   | `/api/applications/akbank`        | Create application for Akbank    |
| POST   | `/api/applications/garanti`       | Create application for Garanti   |
| GET    | `/api/applications`               | List all applications            |
| GET    | `/api/applications/{id}`          | Get application by ID            |
| GET    | `/api/applications/user/{email}`  | Get applications by user email   |
| PUT    | `/api/applications/{id}`          | Update application               |
| DELETE | `/api/applications/{id}`          | Delete application               |

### Banks (`/api/banks`)
| Method | Endpoint          | Description     |
| :----- | :---------------- | :-------------- |
| POST   | `/api/banks`      | Create a bank   |
| GET    | `/api/banks`      | List all banks  |
| GET    | `/api/banks/{id}` | Get bank by ID  |
| PUT    | `/api/banks/{id}` | Update bank     |
| DELETE | `/api/banks/{id}` | Delete bank     |

### Loans (`/api/loans`)
| Method | Endpoint          | Description     |
| :----- | :---------------- | :-------------- |
| POST   | `/api/loans`      | Create a loan   |
| GET    | `/api/loans`      | List all loans  |
| GET    | `/api/loans/{id}` | Get loan by ID  |
| PUT    | `/api/loans/{id}` | Update loan     |
| DELETE | `/api/loans/{id}` | Delete loan     |

### Credit Cards (`/api/creditcards`)
| Method | Endpoint                                  | Description                    |
| :----- | :---------------------------------------- | :----------------------------- |
| POST   | `/api/creditcards`                        | Create a credit card           |
| GET    | `/api/creditcards`                        | List all credit cards          |
| GET    | `/api/creditcards/{id}`                   | Get credit card by ID          |
| GET    | `/api/creditcards/byBankName/{bankName}`  | Get credit cards by bank name  |
| PUT    | `/api/creditcards/{id}`                   | Update credit card             |
| DELETE | `/api/creditcards/{id}`                   | Delete credit card             |

### Campaigns (`/api/campaigns`)
| Method | Endpoint                                              | Description                          |
| :----- | :---------------------------------------------------- | :----------------------------------- |
| POST   | `/api/campaigns`                                      | Create a campaign                    |
| GET    | `/api/campaigns`                                      | List all campaigns                   |
| GET    | `/api/campaigns/{id}`                                 | Get campaign by ID                   |
| GET    | `/api/campaigns/byCreditCardName/{creditCardName}`    | Get campaigns by credit card name    |
| PUT    | `/api/campaigns/{id}`                                 | Update campaign                      |
| DELETE | `/api/campaigns/{id}`                                 | Delete campaign                      |

### Addresses (`/api/addresses`)
| Method | Endpoint              | Description        |
| :----- | :-------------------- | :----------------- |
| POST   | `/api/addresses`      | Create an address  |
| GET    | `/api/addresses`      | List all addresses |
| GET    | `/api/addresses/{id}` | Get address by ID  |
| PUT    | `/api/addresses/{id}` | Update address     |
| DELETE | `/api/addresses/{id}` | Delete address     |

## Design Patterns

- **Strategy** — Notification dispatching (`EmailNotificationStrategy`, `SmsNotificationStrategy`, `MobileNotificationStrategy`) selected at runtime via `NotificationStrategyFactory` and executed through `NotificationContext`
- **Service-Interface Segregation** — Every service implements a corresponding interface (`IUserService`, `IApplicationService`, etc.) for loose coupling
- **Database per Service** — Each microservice owns its own PostgreSQL schema
- **API Gateway** — Single entry point abstracting internal topology
- **Event-Driven** — Async communication between Kredinbizde Service and Notification Service via RabbitMQ; Kafka for application event streaming
- **Global Exception Handling** — Centralized `GlobalExceptionHandler` with custom `KredinbizdeException` and structured error DTOs

## How to Run

### Prerequisites
- Docker & Docker Compose

### Start

```bash
git clone https://github.com/burakyigitaslan/kredinbizde-application.git
cd kredinbizde-application
docker-compose up -d
```

### Access Points

| Resource            | URL                                              |
| :------------------ | :----------------------------------------------- |
| API Gateway         | http://localhost:8084                             |
| Eureka Dashboard    | http://localhost:8761                             |
| RabbitMQ Management | http://localhost:15672 (admin / 123456)           |
| Kredinbizde Swagger | http://localhost:8080/swagger-ui/index.html       |
| Akbank Swagger      | http://localhost:8081/swagger-ui/index.html       |
| Garanti Swagger     | http://localhost:8083/swagger-ui/index.html       |

## Project Structure

```
kredinbizde-application/
├── kredinbizde-service/          # Core platform service
│   ├── controller/               # REST controllers (7)
│   ├── service/                  # Business logic
│   │   └── interfaces/           # Service interfaces (7)
│   ├── model/                    # JPA entities (10)
│   │   └── constant/             # Column constant classes (7)
│   ├── repository/               # Spring Data JPA repositories (8)
│   ├── client/                   # Feign clients (Akbank, Garanti)
│   │   └── dto/                  # Client DTOs (5)
│   ├── producer/                 # Kafka & RabbitMQ producers
│   │   ├── dto/                  # Producer DTOs
│   │   ├── enums/                # Notification enums
│   │   └── strategy/             # Notification strategy pattern
│   │       └── impl/             # Email, SMS, Mobile strategies + Factory
│   ├── configuration/            # Spring configs (2)
│   ├── converter/                # DTO converters
│   ├── dto/                      # Data transfer objects
│   ├── enums/                    # Domain enums (4)
│   ├── exceptions/               # Custom exceptions + global handler
│   └── kafkaconsumer/            # Kafka consumers
├── akbank-service/               # Akbank bank integration
├── garanti-service/              # Garanti bank integration
├── notification-service/         # Notification consumer (RabbitMQ)
├── kredinbizde-discovery/        # Eureka discovery server
├── kredinbizde-gw/               # Spring Cloud Gateway
└── docker-compose.yaml           # Full stack orchestration (11 containers)
```
