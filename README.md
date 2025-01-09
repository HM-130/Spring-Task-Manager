# Task Management API

This is a Spring Boot RESTful API for managing tasks. It allows users to perform CRUD (Create, Read, Update, Delete) operations on tasks. The API supports features like validation, pagination, and custom error handling.

## Features

- **Create**: Add a new task with details like name, description, priority, deadline, and completion status.
- **Read**:
  - Retrieve all tasks with pagination support.
  - Get tasks by specific attributes such as ID, priority, or deadline.
- **Update**: Modify the details of an existing task.
- **Delete**: Remove a task by its ID.
- **Validation**: Ensures all input data meets the required constraints.
- **Error Handling**: Provides meaningful error messages for common issues.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building RESTful APIs.
- **Hibernate/JPA**: ORM for database interaction.
- **H2 Database**: In-memory database for development and testing.
- **cURL**: Command-line tool for testing the API.

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 17 or higher.
- **Maven** for dependency management and build automation.

---

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

3. The application will start at `http://localhost:8080`.

---

## API Endpoints

### Task Endpoints

| Method | Endpoint               | Description                                |
|--------|------------------------|--------------------------------------------|
| GET    | `/tasks`               | Retrieve all tasks (supports pagination). |
| GET    | `/tasks/{id}`          | Retrieve a task by its ID.                |
| GET    | `/tasks/priority/{priority}` | Retrieve tasks by priority.           |
| GET    | `/tasks/deadline/{deadline}` | Retrieve tasks by deadline (format: `yyyy-MM-dd`). |
| POST   | `/tasks`               | Create a new task.                        |
| PUT    | `/tasks/{id}`          | Update an existing task by its ID.        |
| DELETE | `/tasks/{id}`          | Delete a task by its ID.                  |

---

## Example cURL Commands

### Create a Task
```bash
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d "{\"name\":\"Learn Java\",\"description\":\"Complete Java tutorials\",\"priority\":\"High\",\"deadline\":\"2025-01-15\",\"completed\":false}"
```

### Get All Tasks
```bash
curl -X GET http://localhost:8080/tasks
```

### Get Task by ID
```bash
curl -X GET http://localhost:8080/tasks/1
```

### Update a Task
```bash
curl -X PUT http://localhost:8080/tasks/1 \
-H "Content-Type: application/json" \
-d "{\"name\":\"Learn Advanced Java\",\"description\":\"Complete advanced tutorials\",\"priority\":\"Medium\",\"deadline\":\"2025-02-01\",\"completed\":true}"
```

### Delete a Task
```bash
curl -X DELETE http://localhost:8080/tasks/1
```

---

## Error Handling

### Common Error Responses

- **400 Bad Request**: Input validation failed.
- **404 Not Found**: Resource not found.
- **500 Internal Server Error**: Unexpected error.

---

## Future Enhancements

- Add user authentication and authorization.
- Implement additional filters for tasks (e.g., by completion status).
- Add support for external databases like MySQL or PostgreSQL.
- Develop a front-end client for easier interaction.

---

## License

This project is licensed under the MIT License. You are free to use, modify, and distribute it.

---

## Author

**Henry**  
A 14-year-old aspiring software engineer with a passion for programming. 🚀
