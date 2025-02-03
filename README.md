# E-Commerce API (Spring Boot Microservices)

## Overview
This project is a **scalable e-commerce API** built using **Spring Boot microservices architecture**. It features modular services for handling products, orders, and authentication, with event-driven communication and distributed tracing.

## Tech Stack
- **Backend:** Spring Boot, Spring Cloud Gateway, Spring Cloud Config Server
- **Communication:** Kafka (Event-Driven Communication), OpenFeign, RestTemplate
- **Service Management:** Eureka Server (Service Discovery)
- **Security:** Keycloak (Authentication & Authorization)
- **Observability:** Zipkin (Distributed Tracing)
- **Deployment:** Docker, Docker Compose

## Features
- **Microservices Architecture:** Independent services for products, orders, and authentication.
- **Event-Driven Communication:** Kafka ensures asynchronous and decoupled interactions between services.
- **Centralized API Gateway:** Spring Cloud Gateway for request routing and security.
- **Service Discovery & Load Balancing:** Eureka Server for dynamic service registration.
- **Centralized Configuration Management:** Spring Cloud Config Server.
- **Secure Authentication & Authorization:** Keycloak integration.
- **Distributed Tracing:** Zipkin for monitoring and debugging.
- **Containerized Deployment:** Docker and Docker Compose for easy scalability.

## Installation & Setup
### Prerequisites
- Java 17+
- Docker & Docker Compose
- Kafka (Configured in Docker)
- Keycloak (Configured in Docker)

### Steps to Run
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/ecommerce-api.git
   cd ecommerce-api
   ```
2. **Start Services Using Docker Compose:**
   ```bash
   docker-compose up -d
   ```
3. **Access Services:**
   - API Gateway: `http://localhost:8222`
   - Eureka Dashboard: `http://localhost:8761`
   - Keycloak: `http://localhost:8080`
   - Zipkin: `http://localhost:9411`
 
