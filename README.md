# Stock Trading System

A production-inspired Event Driven Microservices application built using Spring Boot, Kafka, PostgreSQL, Redis, and Spring Cloud.

## Architecture

```
                API Gateway
                     │
                     ▼
              Order Service
                     │
              Kafka Broker
      ┌──────────┼───────────┐
      ▼          ▼           ▼
 Risk Service Portfolio Service Notification Service
```

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Redis
- Spring Cloud Gateway
- Docker
- Maven

## Services

- Order Service
- Risk Service
- Portfolio Service
- Notification Service
- API Gateway

## Status

🚧 Under Development