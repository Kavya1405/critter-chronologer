# Critter Chronologer – Pet & Employee Scheduling System

Critter Chronologer is a Spring Boot–based backend application designed to manage customers, pets, employees, and schedules.  
This project showcases enterprise-level backend architecture using controllers, services, repositories, DTOs, and relational mappings.

---

## 🚀 Features

### 🐾 Customer & Pet Management
- Add customers and associate multiple pets to each customer  
- Store pet details such as type, birthdate, notes, and behavior  

### 👨‍💼 Employee Management
- Register employees with specific skills  
- Define employee availability  
- Assign employees to scheduled activities  

### 📅 Scheduling System
- Create schedules connecting employees, pets, and activities  
- Retrieve schedules by:
  - Customer
  - Pet
  - Employee  

### 🧩 Clean Layered Architecture
- **Controller layer** for API endpoints  
- **Service layer** for business logic  
- **Repository layer** for database access  
- **DTOs + Mappers** for clean data transfer  
- **Entities** mapped with JPA/Hibernate  

---

## 🛠️ Tech Stack

- **Java 17**  
- **Spring Boot**  
- **Spring MVC**  
- **Spring Data JPA & Hibernate**  
- **MySQL / H2 Database**  
- **Maven**  
- **Postman (API Testing)**  

---

## 📁 Project Structure

```
src
 ├── main
 │    ├── java/com/udacity/jdnd/course3/critter
 │    │     ├── controller
 │    │     │     ├── CustomerController.java
 │    │     │     ├── EmployeeController.java
 │    │     │     ├── PetController.java
 │    │     │     └── ScheduleController.java
 │    │     ├── dto
 │    │     │     ├── CustomerDTO.java
 │    │     │     ├── EmployeeDTO.java
 │    │     │     ├── PetDTO.java
 │    │     │     └── ScheduleDTO.java
 │    │     ├── entity
 │    │     │     ├── Customer.java
 │    │     │     ├── Employee.java
 │    │     │     ├── EmployeeSkill.java
 │    │     │     ├── Pet.java
 │    │     │     ├── PetType.java
 │    │     │     └── Schedule.java
 │    │     ├── mapper
 │    │     │     ├── CustomerMapper.java
 │    │     │     ├── EmployeeMapper.java
 │    │     │     ├── PetMapper.java
 │    │     │     └── ScheduleMapper.java
 │    │     ├── repository
 │    │     │     ├── CustomerRepository.java
 │    │     │     ├── EmployeeRepository.java
 │    │     │     ├── PetRepository.java
 │    │     │     └── ScheduleRepository.java
 │    │     └── service
 │    │           ├── CustomerService.java
 │    │           ├── EmployeeService.java
 │    │           ├── PetService.java
 │    │           ├── Scheduleservice.java
 │    │           └── CritterApplication.java
 │    └── resources
 │          ├── application.properties
 │          └── Udacity.postman_collection.json
 └── test
      └── java/com/udacity/jdnd/course3/critter
            └── CritterFunctionalTest.java

pom.xml
HELP.md
mvnw
mvnw.cmd
README.md
```
