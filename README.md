

# Kredinbizde Application

Welcome to the Kredinbizde Application project! This project demonstrates a microservices architecture for managing applications and users, along with integration with external services such as Akbank and Garanti.

## Table of Contents

- [Introduction](#introduction)
- [Architecture](#architecture)
- [Services](#services)
- [Setup](#setup)


## Introduction

The project showcases a microservices architecture, which is a distributed approach to building software systems. In this architecture, the application is divided into multiple loosely coupled services, each responsible for a specific set of functionalities. These services communicate with each other through APIs and message queues.

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

1. Clone the repository.


2. Start the services using Docker Compose:

    ```bash
    docker-compose up -d
    ```

3. Access the services through their respective ports as described in the [Services](#services) section.


