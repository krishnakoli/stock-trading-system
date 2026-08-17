# Stock Trading System

A production-inspired **Event-Driven Microservices** application built using
Spring Boot, Apache Kafka, PostgreSQL, Spring Cloud Gateway, Eureka,
Docker, and Maven.

The project demonstrates how a distributed trading workflow can be designed
using **Microservices, Event-Driven Architecture, Service Discovery,
Client-Side Load Balancing, API Gateway, Containerization, and
Fault-Tolerant communication patterns.**

---

# Architecture

```text
                             ┌──────────────────┐
                             │      Client      │
                             └────────┬─────────┘
                                      │
                                      │ HTTP
                                      ▼
                           ┌─────────────────────┐
                           │     API Gateway     │
                           │       :8080         │
                           │                     │
                           │ • Routing           │
                           │ • CORS              │
                           │ • Logging           │
                           │ • Correlation ID    │
                           │ • Rate Limiting     │
                           │ • Exception Handler │
                           └──────────┬──────────┘
                                      │
                                      │ lb://SERVICE
                                      ▼
                         ┌─────────────────────────┐
                         │      Eureka Server      │
                         │         :8761           │
                         │                         │
                         │   Service Discovery     │
                         └───────────┬─────────────┘
                                     │
                 ┌───────────────────┼───────────────────┐
                 │                   │                   │
                 ▼                   ▼                   ▼

        ┌────────────────┐  ┌────────────────┐  ┌────────────────┐
        │ Order Service  │  │  Risk Service  │  │Portfolio Service│
        │                │  │                │  │                │
        │ Order #1       │  │ Risk #1        │  │ Portfolio #1   │
        │ Order #2       │  │ Risk #2        │  │ Portfolio #2   │
        │ Order #3       │  │ Risk #3        │  │ Portfolio #3   │
        └───────┬────────┘  └────────────────┘  └────────────────┘
                │
                │
                ▼
        ┌──────────────────────────────────────────┐
        │                  Kafka                   │
        │                                          │
        │ trade-order                              │
        │ risk-approved                            │
        │ risk-rejected                            │
        │ portfolio-update-info                    │
        └──────────────────────────────────────────┘
                │
                │
                ▼
        ┌───────────────────────┐
        │ Notification Service  │
        │                       │
        │ Notification #1       │
        │ Notification #2       │
        │ Notification #3       │
        └───────────────────────┘


        ┌──────────────────────┐
        │      PostgreSQL      │
        │                      │
        │ Order Data           │
        │ Portfolio Data       │
        │ Risk Data            │
        └──────────────────────┘


        ┌──────────────────────┐
        │       Docker         │
        │                      │
        │ Containers           │
        │ Networking           │
        │ Service Isolation    │
        │ Infrastructure       │
        └──────────────────────┘
        
End-to-End Order Flow

The application follows an event-driven trading workflow.

Client
  │
  │ POST /orders
  ▼
API Gateway
  │
  │ Route request
  ▼
Order Service
  │
  ├── Validate request
  │
  ├── Persist Order
  │
  └── Publish OrderPlacedEvent
          │
          ▼
       Kafka
   trade-order
          │
          ▼
     Risk Service
          │
          ├── Symbol Validation
          ├── Quantity Validation
          └── Market Hours Validation
          │
          ├───────────────┐
          │               │
          ▼               ▼
    risk-approved    risk-rejected
          │               │
          ▼               ▼
   Portfolio Service   Notification
          │
          │
          ▼
   Portfolio Updated
          │
          ▼
   Portfolio Event
          │
          ▼
   Notification Service

The important architectural principle is that services do not need to
directly call each other for the main business workflow.

Instead, they communicate through Kafka events.