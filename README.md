# Student-Demo-Microservice

A simple Spring Boot microservice that allows adding new students and retrieving a student by ID via REST APIs.

## Features

- Add a new student
- Retrieve a student by ID

## REST Endpoints

| HTTP Method | Endpoint        | Description                 |
|-------------|----------------|-----------------------------|
| POST        | /student       | Add a new student           |
| GET         | /student/{id}  | Retrieve a student by ID    |

## Request / Response Examples

### Add a student

**Request:**

```http
POST /student
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 20
}
