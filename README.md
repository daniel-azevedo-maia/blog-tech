# Blog Tech

A web-based blog application developed with Java 17 and Spring Boot. Users can create an account, sign in, publish articles with an optional image, and browse all published posts.

## Features

* User registration with validation and BCrypt password encryption
* Secure authentication and logout with Spring Security
* Post creation with an optional image
* Public pages for browsing and reading posts
* Ready-to-use local persistence with H2
* PostgreSQL profile for production-like environments
* Context, security, user, and post tests
* Dockerfile, Docker Compose, and CI pipeline

## Technologies

* Java 17
* Spring Boot 3.5
* Spring MVC, Data JPA, Security, and Validation
* Thymeleaf
* H2 and PostgreSQL
* Maven
* JUnit 5 and MockMvc

## Running Locally

Requirement: JDK 17 or later.

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will be available at `http://localhost:8080`.

By default, it uses an H2 database persisted in the `./data` directory. No external database installation is required to get started.

## Running the Tests

```bash
./mvnw clean verify
```

## Running with PostgreSQL

Create a database named `blogtech` and run:

```bash
export SPRING_PROFILES_ACTIVE=postgres
export DB_URL=jdbc:postgresql://localhost:5432/blogtech
export DB_USER=postgres
export DB_PASSWORD=postgres
./mvnw spring-boot:run
```

## Running with Docker Compose

```bash
docker compose up --build
```

Docker Compose starts the application and a PostgreSQL database that is configured automatically.

## Project Structure

```text
src/main/java/com/danielazevedo/blogtech
├── application
│   ├── controller
│   ├── dto
│   ├── exception
│   └── service
├── domain
│   ├── model
│   └── repository
└── infrastructure
    ├── config
    └── security
```

## Security

* Passwords are never stored as plain text.
* CSRF protection remains enabled.
* Registration, login, and post reading are publicly accessible.
* Post creation and user listing require authentication.
* Logout is performed through a POST request.
