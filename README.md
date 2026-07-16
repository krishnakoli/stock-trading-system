# Stock Trading System

A production-inspired Event Driven Microservices application built using Spring Boot, Kafka, PostgreSQL, Redis, and Spring Cloud.

## Architecture

```
                    ┌──────────────────┐
                    │   Client/API     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │  Order Service   │
                    │   Port: 8080     │
                    └────────┬─────────┘
                             │
                    1. Save Order
                             │
                             ▼
                      Kafka: trade-order
                             │
                             ▼
                    ┌──────────────────┐
                    │   Risk Service   │
                    │   Port: 8081     │
                    └────────┬─────────┘
                             │
                    2. Validate Risk
                      ┌──────┴──────┐
                      │             │
                      ▼             ▼
             risk-approved     risk-rejected
                      │             │
                      ▼             ▼
             ┌──────────────┐  Notification
             │   Portfolio  │
             │   Service    │
             │   8082       │
             └──────┬───────┘
                    │
             3. Update Portfolio
                    │
                    ▼
             portfolio-update-info
                    │
                    ▼
             ┌──────────────────┐
             │ Notification     │
             │ Service :8083    │
             └──────────────────┘
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
