### LinkVault

Simple Spring Boot + HSQLDB backend for saving links with categories and tags.  
Implements basic CRUD and returns clean DTOs (MapStruct).  
Built for the graduation project.

## Table of Contents

1. [Description](#description)
2. [Prerequisites](#prerequisites)
3. [Getting Started](#getting-started)
    - [Clone the repository](#clone-the-repository)
    - [Build & Run (IntelliJ IDEA)](#build--run-intellij-idea)
4. [Swagger UI](#swagger-ui)
5. [Configuration](#configuration)
6. [Database Initialization](#database-initialization)
7. [Available endpoints](#available-endpoints)
8. [Project structure](#project-structure)
9. [Example](#example)

---

## Description

Implements basic CRUD operations for managing saved links.  
Uses Spring Boot, Spring Data JPA, HSQLDB (in-memory), and MapStruct.

---

## Prerequisites

- JDK 21
- IntelliJ IDEA
- Gradle wrapper (included)

---

## Getting Started

### Clone the repository

```bash
git clone https://github.com/MKdot1/linkvault.git
cd linkvault
```

---

### Build & Run (IntelliJ IDEA)

- Open the project in IntelliJ IDEA
- Run `LinkvaultApplication` or use Gradle wrapper:
  ```bash
  ./gradlew bootRun
  ```
  App starts at: http://localhost:8080

---

## Swagger UI

Interactive API documentation available at:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Configuration

HSQLDB in-memory database is preconfigured.

- **User:** `sa`
- **Password:** *(empty)*
- **URL:** `jdbc:hsqldb:hsql://localhost:9001/mydb`

---

## Database Initialization

Spring Boot automatically runs:

- **schema.sql** → defines tables (`LINK`, `CATEGORY`, `TAG`, `LINK_TAG`)
- **data.sql** → seeds categories, tags, and example links

---

---

## Available Endpoints

| Method | Path       | Description                  |
|--------|------------|------------------------------|
| GET    | /link/all  | Retrieve all links           |
| GET    | /link/{id} | Retrieve a single link by ID |
| POST   | /link      | Create a new link            |
| PUT    | /link/{id} | Update an existing link      |
| DELETE | /link/{id} | Delete a link by ID          |

---

## Project Structure

```
linkvault/
└── src/
    ├── main/
    │   ├── java/eu/itcrafters/linkvault/infrastructure/
    │   │   ├── controller/        # REST controllers & DTOs
    │   │   ├── service/           # Business logic services
    │   │   ├── persistence/       # Entities, repositories, mappers
    │   │   └── errorhandling/     # Exception handling & error models
    │   └── resources/
    │       ├── application.properties
    │       ├── schema.sql         # DB schema
    │       └── data.sql           # Seed data
    ├── test/
    │   └── java/eu/itcrafters/linkvault/
    │       └── LinkvaultApplicationTests.java
├── build.gradle
├── settings.gradle
└── README.md
```

## Example

**POST** `/link`

```json
{
  "linkAddress": "https://multi-tag-example.com",
  "description": "Example with multiple tags",
  "workflowStatus": "waiting",
  "visibility": "private",
  "categoryId": 1,
  "tagIds": [
    2,
    3,
    5
  ]
}
```
