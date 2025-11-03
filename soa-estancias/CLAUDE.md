# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.6 rental property management application for managing apartments/properties, clients, reservations, availability, and payments. The project uses Java 17 and MySQL as the database.

**Important:** The codebase has package naming inconsistencies. The source files declare packages under `com.blue.apartamentos.*` but the directory structure and POM use `com.depas.demo`. This mismatch will cause compilation errors until resolved.

## Build and Run Commands

Build the project:
```bash
./mvnw clean install
```

Run the application:
```bash
./mvnw spring-boot:run
```

Run tests:
```bash
./mvnw test
```

Run a single test class:
```bash
./mvnw test -Dtest=ClassName
```

Run a single test method:
```bash
./mvnw test -Dtest=ClassName#methodName
```

Package the application:
```bash
./mvnw package
```

## Architecture

### Layered Architecture

The application follows a standard three-tier Spring Boot architecture:

1. **Controllers** (`controllers/`): REST API endpoints
   - Handle HTTP requests/responses
   - Use `@RestController` and `@RequestMapping`
   - Return `ResponseEntity` for proper HTTP status codes
   - Example: `ClienteController` handles CRUD operations for clients at `/api/clientes`

2. **Services** (`services/`): Business logic layer
   - Annotated with `@Service`
   - Inject repositories via `@Autowired`
   - Contain business logic and orchestration
   - Example: `ClienteService` provides methods like `getAllClientes()`, `saveCliente()`

3. **Repositories** (`repositories/`): Data access layer
   - Extend `JpaRepository<Entity, ID>`
   - Annotated with `@Repository`
   - Provide automatic CRUD operations via Spring Data JPA
   - Example: `IClienteRepository` extends `JpaRepository<ClienteModel, Long>`

4. **Models** (`models/`): JPA entities
   - Annotated with `@Entity` and `@Table`
   - Map to database tables
   - Use Jakarta Persistence annotations

### Domain Models

**ClienteModel** (clients table):
- Represents users who can be clients, property owners (`PROPIETARIO`), or companies
- Key fields: `tipo_cliente` (enum), `email` (unique), `ine` (unique), `estatus`, `password`
- Has enums: `TipoCliente` (PROPIETARIO, CLIENTE, EMPRESA) and `ClienteStatus` (ACTIVO, INACTIVO, SUSPENDIDO)

**PropiedadModel** (propiedades table):
- Represents rental properties (apartments, houses, etc.)
- Has `@ManyToOne` relationship with `ClienteModel` (owner)
- Uses `@JsonIgnoreProperties` on the relationship to avoid lazy loading issues
- Has `@PrePersist` and `@PreUpdate` callbacks for automatic timestamp management
- Key fields: location data (lat/long, address), pricing, capacity, amenities, rules
- Enums: `TipoPropiedad` (ACTIVO, INACTIVO, SUSPENDIDO) and `EstadoHab` (DISPONIBLE, NO_DISPONIBLE, MANTENIMIENTO)

**DisponibilidadModel** (disponibilidades table):
- Manages property availability periods
- Supports special pricing for specific date ranges
- Missing relationship annotation to `PropiedadModel` (commented out `id_propiedad`)

**PagosModel** (pagos table):
- Handles payment records for reservations
- Missing relationship to `ReservaModel` (referenced but model doesn't exist yet)
- Enums: `MetodoPago` (payment methods) and `EstadoPago` (payment status)

### Database Configuration

Database connection is configured in `src/main/resources/application.properties`. Currently minimal - you'll need to add:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/database_name
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Key Development Notes

### Package Naming Issue

**Critical:** Before building, fix the package mismatch:
- Files declare packages as `com.blue.apartamentos.*`
- POM and directory structure use `com.depas.demo`
- Either update all Java files to use `com.depas.demo` or update the directory structure to match `com.blue.apartamentos`

### Missing Models

The codebase references but hasn't implemented:
- `ReservaModel` (reservations) - referenced in `PagosModel`

### Incomplete Models

- `DisponibilidadModel` and `PagosModel` lack getters/setters and constructors
- Both have commented-out relationship fields that need proper `@ManyToOne`/`@OneToMany` annotations

### JPA Relationships

When adding relationships between entities:
- Use `@ManyToOne(fetch = FetchType.LAZY)` for the many side
- Add `@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})` to avoid Jackson serialization issues
- Consider adding bidirectional relationships with `@OneToMany(mappedBy = "field")` on the one side
- Use `@JoinColumn(name = "column_name")` to specify the foreign key column

### Timestamp Management

PropiedadModel demonstrates automatic timestamp handling with:
- `@PrePersist` for setting creation timestamp
- `@PreUpdate` for updating modification timestamp
- Consider applying this pattern to other models
