# 🛒 E-Commerce Backend API

A Spring Boot-based backend REST API for an e-commerce application. This project handles product management, user operations, order handling, and more.

---

## 🚀 Features

- RESTful API using Spring Boot
- User & Role management
- Product CRUD operations
- Order processing
- JWT-based authentication (optional)
- Dockerized for easy deployment
- Clean architecture (Controller → Service → Repository → Entity/DTO)

---

## 📊 Current Progress

| Module              | Status       | Notes                                    |
|---------------------|--------------|-------------------------------------------|
| Project Setup       | ✅ Done       | Spring Boot initialized with Maven        |
| Product Module      | ✅ Done       | CRUD operations complete                  |
| DTOs & ModelMapper  | ✅ Done       | DTO to Entity conversion via ModelMapper |
| Exception Handling  | ✅ Done       | Global handler with custom exceptions     |
| File Upload (Images)| ✅ Done       | Handled with `@ModelAttribute`           |
| Docker Setup        | ✅ Done       | Dockerfile created and working           |
| Git Integration     | ✅ Done       | Project pushed to GitHub                 |
| application.properties | 🔒 Ignored | Not pushed to repo (kept locally)        |
| JWT Security        | ⏳ In Progress | To be added                              |
| Order Module        | ⏳ Pending    | Coming soon                              |

> Update this section as you progress!

---

## 🧰 Tech Stack

- Java 17
- Spring Boot
- Maven
- Spring Data JPA / MongoDB
- Spring Security (optional)
- MySQL / MongoDB
- Docker

---

## 📦 Getting Started

### 🔧 Prerequisites

- Java 17+
- Maven
- Docker (optional)
- MySQL or MongoDB running locally (optional)

---

### 💻 Run Locally

#### 1. Clone the repo

git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>

#### 2. Configure Database
Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

##### 3. Run the App
# Run with Maven
./mvnw spring-boot:run

# OR package and run the JAR
./mvnw package
java -jar target/*.jar
