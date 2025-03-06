# Library Management DAO

A foundational Java project implementing the **Data Access Object (DAO)** design pattern to manage books in a library system. This repository represents **Level 1** of a multi-tiered learning journey, focusing on clean data access abstraction and basic CRUD operations (Create, Read, Update, Delete). 

## Purpose
The goal is to establish a robust data layer for a library management system, isolating data access logic from the rest of the application. This project serves as a stepping stone for adding advanced layers like Service, Controller, and Spring in future levels.

## Features
- **Book Management**: Add, find, update, and delete books using a simple in-memory store (`ArrayList`).
- **DAO Pattern**: Demonstrates separation of concerns by encapsulating data access logic.
- **Extensibility**: Designed to be easily extended with additional layers and persistence mechanisms.

## Technologies
- **Java**: Core language for implementation.
- **No External Dependencies**: Pure Java with no frameworks (yet!).

## Project Structure
.
├── src/
├── ├── main/
├── │   ├── java/
├── │   │   ├── model/        # Book entity
├── │   │   ├── dao/          # DAO interface and implementation
├── │   │   └── Main.java     # Entry point for testing
└── └── README.md

## Getting Started
1. Clone the repository: `git clone https://github.com/[your-username]/library-management-dao.git`
2. Open in your favorite IDE (e.g., IntelliJ, Eclipse).
3. Run `Main.java` to see the DAO in action.

## Next Steps
This is the first level of a multi-stage project. Stay tuned for:
- **Level 2**: Adding a Service layer for business logic.
- **Level 3**: Integrating Spring Framework.
- **Level 4**: Full implementation with Request/Response and Mapper patterns.

## Contributions
Feel free to fork, experiment, and submit pull requests. Feedback is always welcome!

---
Developed as part of a mentorship-inspired learning path.
