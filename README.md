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

- **Loan Aggregation** — Compare and apply for loans across multiple bank partners
- **Credit Card Management** — Browse and manage credit card offerings
- **Campaign Engine** — Bank-specific campaigns with sector and product targeting
- **Multi-Channel Notifications** — Email, SMS, and mobile push via Strategy pattern
- **Event-Driven Messaging** — RabbitMQ for notifications, Kafka for application events
- **Service Discovery** — Eureka-based dynamic service registration
- **API Gateway** — Centralized routing and entry point
- **Caching** — Redis caching for high-read operations
- **Unit Tested** — Service-layer tests for all core domains

## Tech Stack

| Layer          | Technology                              |
| :------------- | :-------------------------------------- |
| Language       | Java 17, Spring Boot 3                  |
| Cloud          | Spring Cloud (Gateway, Eureka, OpenFeign) |
| Database       | PostgreSQL (database-per-service)       |
| Messaging      | Apache Kafka, RabbitMQ                  |
| Caching        | Redis                                   |
| Containerization | Docker, Docker Compose                |
| API Docs       | Swagger / OpenAPI                       |
| Testing        | JUnit 5, Mockito                        |

## Services

| Service | Port | Description | Docs |
| :--- | :--- | :--- | :--- |
| **API Gateway** | `8084` | Routes all external requests to downstream services | — |
| **Kredinbizde Service** | `8080` | Core platform — users, applications, loans, campaigns, credit cards | [Swagger](http://localhost:8080/swagger-ui/index.html) |
| **Akbank Service** | `8081` | Akbank loan product integration | [Swagger](http://localhost:8081/swagger-ui/index.html) |
| **Garanti Service** | `8083` | Garanti loan product integration | [Swagger](http://localhost:8083/swagger-ui/index.html) |
| **Notification Service** | `8082` | Consumes RabbitMQ messages and dispatches notifications | — |
| **Discovery Server** | `8761` | Eureka service registry | [Dashboard](http://localhost:8761) |

## Domain Model (Kredinbizde Service)

`User` · `Application` · `Bank` · `Loan` · `CreditCard` · `Campaign` · `Address` · `Log`

## API Endpoints

### Users
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/users` | Create a new user |
| GET | `/api/users` | List all users |
| GET | `/api/users/{email}` | Get user by email |

### Applications
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/applications` | Create a loan application |
| GET | `/api/applications` | List all applications |
| GET | `/api/applications/user/{userId}` | Get applications by user |
| GET | `/api/applications/{applicationId}` | Get application by ID |

### Banks
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/banks` | Create a bank |
| GET | `/api/banks` | List all banks |

### Loans
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/loans` | Create a loan |
| GET | `/api/loans` | List all loans |

### Credit Cards
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/credit-cards` | Create a credit card |
| GET | `/api/credit-cards` | List all credit cards |
| GET | `/api/credit-cards/{creditCardId}` | Get credit card by ID |

### Campaigns
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/campaigns` | Create a campaign |
| GET | `/api/campaigns` | List all campaigns |
| GET | `/api/campaigns/{campaignId}` | Get campaign by ID |

### Addresses
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/api/addresses` | Create an address |
| GET | `/api/addresses` | List all addresses |

## Design Patterns

- **Strategy** — Notification dispatching (`EmailNotificationStrategy`, `SmsNotificationStrategy`, `MobileNotificationStrategy`) selected at runtime via `NotificationStrategyFactory`
- **Service-Interface Segregation** — Every service implements a corresponding interface for loose coupling
- **Database per Service** — Each microservice owns its own PostgreSQL schema
- **API Gateway** — Single entry point abstracting internal topology
- **Event-Driven** — Async communication between Kredinbizde Service and Notification Service via RabbitMQ; Kafka for application event streaming

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

| Resource | URL |
| :--- | :--- |
| API Gateway | http://localhost:8084 |
| Eureka Dashboard | http://localhost:8761 |
| RabbitMQ Management | http://localhost:15672 (admin / 123456) |
| Kredinbizde Swagger | http://localhost:8080/swagger-ui/index.html |
| Akbank Swagger | http://localhost:8081/swagger-ui/index.html |
| Garanti Swagger | http://localhost:8083/swagger-ui/index.html |

## Project Structure

```
kredinbizde-application/
├── kredinbizde-service/      # Core platform service
│   ├── controller/           # REST controllers (7)
│   ├── service/              # Business logic + interfaces
│   ├── model/                # JPA entities + column constants
│   ├── repository/           # Spring Data JPA repositories
│   ├── client/               # Feign clients (Akbank, Garanti)
│   ├── producer/             # Kafka & RabbitMQ producers
│   │   └── strategy/         # Notification strategy pattern
│   ├── configuration/        # Spring configs
│   ├── converter/            # DTO converters
│   ├── dto/                  # Data transfer objects
│   ├── enums/                # Domain enums
│   ├── exceptions/           # Custom exceptions
│   └── kafkaconsumer/        # Kafka consumers
├── akbank-service/           # Akbank bank integration
├── garanti-service/          # Garanti bank integration
├── notification-service/     # Notification consumer (RabbitMQ)
├── kredinbizde-discovery/    # Eureka discovery server
├── kredinbizde-gw/           # Spring Cloud Gateway
└── docker-compose.yaml       # Full stack orchestration
```
