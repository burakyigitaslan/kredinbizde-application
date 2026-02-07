

# Kredinbizde Application

Welcome to the Kredinbizde Application project! This project demonstrates a microservices architecture for managing applications and users, along with integration with external services such as Akbank and Garanti.

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Services](#services)
- [Setup](#setup)
- [Usage](#usage)
- [Stopping](#stopping)


## Introduction

The project showcases a microservices architecture, which is a distributed approach to building software systems. In this architecture, the application is divided into multiple loosely coupled services, each responsible for a specific set of functionalities. These services communicate with each other through APIs and message queues.

## Technologies

This project is built using the following technologies:

- **Java 21**: Primary programming language
- **Spring Boot 3**: Framework for building microservices
- **Spring Cloud**: Tools for distributed systems and microservices patterns
- **PostgreSQL**: Relational database for persistent storage
- **RabbitMQ**: Message broker for asynchronous communication
- **Kafka**: Distributed event streaming platform
- **Redis**: In-memory data store for caching
- **Docker**: Containerization platform for deployment

## Architecture

The architecture of the project consists of the following components:

- **Discovery Server**: The discovery server, implemented using Eureka, is responsible for service discovery and registration. It allows services to locate and communicate with each other without prior knowledge of network topology.

- **API Gateway**: The API gateway, implemented using Spring Cloud Gateway, acts as a single entry point for clients to access various microservices. It provides routing, filtering, and load balancing functionalities, making it easier to manage and secure the system's APIs.

- **Microservices**: The project comprises multiple microservices, each responsible for specific business functions. These microservices communicate with each other via HTTP RESTful APIs.

- **Databases**: PostgreSQL databases are used to store persistent data for each microservice.

The application consists of several services:

- **Akbank Service**: Manages applications related to Akbank.
- **Garanti Service**: Manages applications related to Garanti.
- **Kredinbizde Service**: Core service managing user-related operations and integrating with Akbank and Garanti services.
- **Notification Service**: Handles notifications using RabbitMQ as a message broker.


## Services

### Kredinbizde Discovery

- **Description**: Service discovery server implemented using Eureka, responsible for service registration and discovery.
- **Port**: 8761
- **Dashboard**: [Eureka Dashboard](http://localhost:8761)

### Kredinbizde Gateway (API Gateway)

- **Description**: API Gateway implemented using Spring Cloud Gateway, acts as a single entry point for clients to access microservices.
- **Port**: 8084
- **Dependencies**: Service Discovery (kredinbizde-discovery)

### Akbank Service

- **Description**: Manages applications related to Akbank.
- **Port**: 8081
- **Dependencies**: PostgreSQL (postgres-akbank), Service Discovery (kredinbizde-discovery)
- **API Documentation**: [Swagger UI](http://localhost:8081/swagger-ui/index.html)

### Garanti Service

- **Description**: Manages applications related to Garanti.
- **Port**: 8083
- **Dependencies**: PostgreSQL (postgres-garanti), Service Discovery (kredinbizde-discovery)
- **API Documentation**: [Swagger UI](http://localhost:8083/swagger-ui/index.html)

### Kredinbizde Service

- **Description**: Core service managing user-related operations and integrating with Akbank and Garanti services.
- **Port**: 8080
- **Dependencies**: PostgreSQL (postgres-kredinbizde), RabbitMQ (rabbitmq), Kafka (kafka), Redis (redis), Service Discovery (kredinbizde-discovery)
- **API Documentation**: [Swagger UI](http://localhost:8080/swagger-ui/index.html)

### Notification Service

- **Description**: Handles notifications using RabbitMQ as a message broker.
- **Port**: 8082
- **Dependencies**: RabbitMQ (rabbitmq), Service Discovery (kredinbizde-discovery)

## Setup

Follow these steps to set up and run the project:

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21**: Required for building the microservices
- **Maven**: Build automation tool for Java projects
- **Docker**: For containerizing and running the services

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/burakyigitaslan/kredinbizde-application.git
    cd kredinbizde-application
    ```

2. Build all microservices using Maven:

    ```bash
    mvn clean package -DskipTests
    ```

    **Note**: This step is crucial as the Dockerfiles copy JAR files from the `target/` directory of each service. Without building first, Docker image creation will fail.

3. Start all services using Docker Compose:

    ```bash
    docker-compose up -d
    ```

4. Wait for all services to start. You can check the status with:

    ```bash
    docker-compose ps
    ```

## Usage

Once all services are running, you can access:

- **API Gateway**: [http://localhost:8084](http://localhost:8084)
- **Discovery Server**: [http://localhost:8761](http://localhost:8761)
- **Kredinbizde Service Swagger**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Akbank Service Swagger**: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Garanti Service Swagger**: [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

## Stopping

To stop all services, run:

```bash
docker-compose down
```


