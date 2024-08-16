# Superonline Sales System Microservice Project

This repository contains a general structure orchestrated around a product registration system, a catalog listed through Redis cache, and an order system. A layered architecture has been applied within the services in a microservice architecture using the Java programming language.

## General Workflow

![senario.png](docs%2Fsenario.png)

## Database Diagram

![sol-microserviceDb.png](docs%2Fsol-microserviceDb.png)

## Microservice Structure

![SolMSFlowDiagram.png](docs%2FSolMSFlowDiagram.png)

## Technologies and Structures Used

- Saga Orchestration Pattern
- Layered architecture service structures
- Request-Response Design Pattern
- Use of annotations such as @Getter, @Setter, etc., instead of the @Data annotation, for modularity and flexibility
- Use of record types for immutable data
- Asynchronous communication between services using Message Broker - RabbitMQ
- Synchronous communication between services via Rest Controllers - Spring Feign Client
- Strong and fast communication between the mock payment service and the order service using gRPC technology
- Concurrency structure in the stock system
- Fast read operations and load optimization with products listed through Redis Cache
- Transactional management and rollback structure
- Fast read operations via NoSQL database for order status checks through the notification service
- Easy service configuration management supporting the CI/CD process via Spring Config Server
- Spring Eureka Server Discovery Client

## Planned Enhancements

- Synchronous communication between Stock and Catalog Services via GraphQL
- SOAP Endpoint structure on the notification service for sample usage
- Optimized dockerized project structure, with separate management of development and production environments
- Identity Service Security Mechanism

## Development

- [Conventional Commits](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
- Package names should be in lower_snake_case format according to Oracle documentation.
- Table and column names in PostgreSQL database services should be in lower_snake_case format according to community standards.
- The var keyword should be avoided as much as possible. Instead, the specific type should be specified. The reason for this is that the var keyword reduces readability.
- Rest APIs have globally recognized standards. You can familiarize yourself with them through the following articles:  [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/), [Best practices for REST API design](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/)