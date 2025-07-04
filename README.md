# Employee Attendance Register

A Spring Boot web service for managing employee attendance in a high-tech medical organization. This application tracks employee presence, absence, and leave details to support payroll processing and reporting. It provides REST APIs for creating, updating, and querying employee and attendance records.

---

# Features

- Add and update employee records
- Classify employees as MEDICAL or NON-MEDICAL
- List all employees with their details
- Filter employees by department
- List available departments
- Register attendance: sign-in, sign-out, sick leave, absence
- View attendance records for an employee within a specific date range
- Clean architecture with service interfaces and implementation classes
- Uses Builder and Strategy design patterns
- Well-structured response handling with custom `ApiResponse`
- Exception handling with `GlobalExceptionHandler`

---

# Technologies

- Java 17  
- Spring Boot 3.5.x  
- Spring Data JPA  
- MySQL  
- Maven  
- Lombok  
- Postman (for API testing)  
- JUnit 5 (for testing)  
- Docker & Docker Compose

---

# Setup Instructions (Without Docker)

1. **Clone the repository**
   git clone https://github.com/IdThompson/employee-attendance-register.git
   cd employee-attendance-register
2. **Configure database**

Ensure MySQL is running locally

Create a database named attendance_db

Update src/main/resources/application.yml if needed

3. **Build and run**

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Access the app

4 **App runs on: http://localhost:8080**

# API Usage (Test with Postman)
Use Postman to test the API endpoints. Below are sample endpoints:

# Method	Endpoint	Description

/api/employees	POST	Add employee

/api/employees/{id}	PUT	Modify employee

/api/employees	GET	List all employees

/api/employees/department	GET	Filter employees by department

/api/departments	GET	List departments

/api/attendance	POST	Register attendance

/api/attendance/register	GET	View attendance by date range

# Docker
**Build and Run with Docker Compose**
1. Ensure Docker is installed and running

2. In the root directory (where Dockerfile and docker-compose.yml are located), run:

bash
Copy
Edit
docker-compose up --build
3. Access Application

App: http://localhost:8080

MySQL runs inside Docker at localhost:3307

# Docker Services
**MySQL**

Port: 3307:3306

DB Name: attendance_db

User: root

Password: root

**Spring Boot App**

Port: 8080:8080

Connects to MySQL container
