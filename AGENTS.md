# AGENTS.md - AI Agent Guidance for Microservice E-Commerce

## Architecture Overview

This is a **Spring Boot microservices** architecture (Java 21, Spring Boot 4.x) with distributed service discovery and configuration management.

### Core Services
- **product-service** (port 8080): Product catalog, PostgreSQL
- **order-service** (port 8081): Order management, PostgreSQL
- **user-service** (port 8082): User profiles, MongoDB
- **eureka-server** (port 8761): Service registry (Spring Cloud Eureka)
- **configserver** (port 8888): Centralized configuration server

### Infrastructure (Docker Compose)
Run `docker-compose up -d` to start:
- PostgreSQL (port 5433): User: `leduyhai`, Pass: `272004`
- MongoDB (port 27017): Database `userdb`
- RabbitMQ (port 5672, UI on 15672): Guest/guest
- PgAdmin (port 5050): Database administration

## Service Discovery & Configuration

**Eureka Registration**: Each service automatically registers on startup via `spring.cloud.starter-netflix-eureka-client` dependency.

**Configuration**: Services fetch config from Config Server on startup:
```yaml
spring:
  config:
    import: optional:configserver:http://localhost:8888
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
```

## Inter-Service Communication

**HTTP Interfaces Pattern** (Spring 6.0+, no Feign):
```java
@HttpExchange
public interface UserServiceClient {
    @GetExchange("/api/users/{id}")
    UserResponse getUserById(@PathVariable String id);
}
```

Services use `RestClient` bean configured with service discovery URL via Eureka client lookup. Example in order-service: `ProductServiceClient` and `UserServiceClient` interfaces.

## Data Layer Patterns

### Repositories
- Extend `JpaRepository<Entity, ID>` for SQL services
- Custom `@Query` methods for complex filtering
- **Soft deletes**: Use `active` boolean flag, query with `WHERE active = true`

```java
@Query("SELECT p FROM product_table p WHERE p.active = true AND LOWER(p.name) LIKE LOWER(...)")
List<Product> searchProduct(@Param("keyword") String keyword);
```

### Mappers
- Static mapper classes in `mapper/` package: `ProductMapper`, `UserMapper`
- Convert Entity ↔ DTO with builder pattern
- Example: `mapToProductResponse(product)`, `mapToProduct(request)`

### Entities
- Extend `BaseEntity` (provides id, timestamps)
- Use Lombok: `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Table name in `@Entity(name = "product_table")`
- JPA import: `jakarta.persistence.*` (not `javax.persistence`)

## Service Layer Conventions

- `@Service` + `@RequiredArgsConstructor` for constructor injection
- Methods return DTOs, not entities
- Handle nulls explicitly; return `null` for not-found (not Optional)
- Example flow: Controller → Service.method() → Repository → Response DTO

```java
public ProductResponse getProductById(Long id) {
    Product product = productRepository.findById(id).orElse(null);
    return product == null ? null : ProductMapper.mapToProductResponse(product);
}
```

## Controller Structure

- `@RestController` + `@RequestMapping("/api/resource")`
- Return `ResponseEntity<T>` with proper status codes
- Standard CRUD: `GET /api/products`, `GET /api/products/{id}`, `POST`, `PUT`, `DELETE`
- Handle not-found: return `ResponseEntity.notFound().build()`

## Build & Deployment

**Build individual service**:
```bash
cd product && mvn clean package
```

**Run service** (after infrastructure up):
```bash
java -jar product/target/product-0.0.1-SNAPSHOT.jar
```

Service will wait for Eureka registration; optional config server failure won't block startup.

## Database Notes

- **Product & Order**: PostgreSQL, Spring Data JPA
- **User**: MongoDB, Spring Data MongoDB (different setup from SQL services)
- H2 console available in dev for testing

## Common Mistakes to Avoid

1. **Don't create shared DTOs module** - each service has its own DTOs
2. **Don't use `@Transactional`** on controllers; use on service layer only
3. **Query names**: Use named queries starting with `find`, `search`, `get`
4. **Soft deletes**: Always check `active = true` in filter queries
5. **Inter-service calls**: Use `@HttpExchange` clients, not direct HTTP in services

## Testing Setup

Services have `spring-boot-starter-webmvc-test` and `spring-boot-starter-data-jpa-test` dependencies. Test classes in `src/test/java/com/ecomerce/{service}/`.

## API Documentation

Each service exposes Swagger UI via `springdoc-openapi-starter-webmvc-ui`:
- Product: `http://localhost:8080/swagger-ui.html`
- Order: `http://localhost:8081/swagger-ui.html`
- User: `http://localhost:8082/swagger-ui.html`

## Key Files & Locations

| Purpose | Pattern |
|---------|---------|
| Service startup | `src/main/java/com/ecomerce/{service}/{Service}Application.java` |
| Config | `src/main/resources/application.yaml` |
| REST client | `src/main/java/com/ecomerce/{service}/clients/*Client.java` |
| CRUD operations | `src/main/java/com/ecomerce/{service}/service/` |
| Database models | `src/main/java/com/ecomerce/{service}/model/` |
