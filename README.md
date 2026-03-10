# 🏥 CareSync - Hospital Management System
### A Seamless Multi-Layered Patient Care & Appointment Governance System

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Node.js](https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)](https://nodejs.org/)
[![Express.js](https://img.shields.io/badge/Express.js-000000?style=for-the-badge&logo=express&logoColor=white)](https://expressjs.com/)
[![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)](https://www.w3.org/html/)
[![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)](https://www.w3.org/Style/CSS/)

**CareSync** is a professional-grade Hospital Appointment Management System that bridges a high-performance **Java Backend** with a modern **Web Interface**. Designed for reliability and speed, it ensures doctors and patients are synchronized with zero scheduling conflicts.

---

## 🚀 Key Modules & Capabilities

### 📅 Advanced Appointment Governance
*   **Doctor Availability Engine**: Real-time checking of doctor schedules (D101, D102, D103) with millisecond precision.
*   **Secure Booking Workflow**: A streamlined process for patients to reserve slots with instant validation.
*   **Conflict Resolution**: Custom `DoctorUnavailableException` handling ensures no double-bookings ever occur.

### ⚙️ Multi-Layer Architecture (Java Core)
*   **Service Layer**: A robust `AppointmentService` managing business logic and exception hierarchies.
*   **Data Access (DAO)**: Optimized `AppointmentDAO` for high-speed availability queries and atomic booking operations.
*   **Encapsulated Data**: Professional `AppointmentBean` structure for type-safe data transfer between layers.

### 🌐 Modern Web Command Center
*   **Responsive UI**: A sleek, accessible frontend for booking and viewing available slots.
*   **RESTful Bridge**: A Node.js API server (Express) acting as a high-speed gateway between the web and Java core.
*   **Real-Time Status**: Instant visual feedback on slot availability and booking confirmations.

---

## 🎨 Design & Interaction
*   **Premium Visuals**: Clean, medical-grade color palette with high-contrast typography for accessibility.
*   **Intuitive UX**: A zero-learning-curve booking interface designed for speed and clarity.
*   **Dynamic Feedback**: Professional success/error notifications powered by the Java exception engine.

---

## 🛠️ Technology Stack
*   **Backend Core**: Java (OOP Principles, Custom Exception Handling, Service/DAO Patterns)
*   **API Gateway**: Node.js & Express.js (High-Speed Request Routing)
*   **Frontend**: Semantic HTML5, CSS3, ES6+ JavaScript
*   **Communication**: REST API (JSON Payload Exchange)
*   **Storage**: High-performance In-Memory model (Ready for SQL/NoSQL expansion)

---

## ⚡ Performance Engineering
*   **Exception-Driven Logic**: Uses a custom exception hierarchy to handle business rules, making the code extremely maintainable and robust.
*   **Stateless API Design**: The Node.js bridge ensures scalable request handling without server-side session overhead.
*   **Optimized DAO Layer**: Dedicated availability checking methods reduce database/memory overhead for lookups.

---

## 📦 Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/NKcoder5/hospital_management_system.git
   cd hospital_management_system
   ```

2. **Compile Java Backend**:
   ```bash
   # Compile all source files into the class path
   javac -d . src/com/wipro/hospital/**/*.java
   ```

3. **Install & Start Node.js Bridge**:
   ```bash
   # Install dependencies
   npm install

   # Launch the API server
   npm start
   ```

4. **Access the System**:
   *   Open your browser and visit `http://localhost:3000`
   *   Start booking appointments with doctors (D101, D102, D103).

---

## 📄 License
Licensed under the **MIT License**.

---
*Created with ❤️ for Advanced Healthcare Management.*
