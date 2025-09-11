# Hospital Appointment Booking System

A complete Hospital Appointment Booking System with Java backend and web frontend that allows patients to book doctor appointments with availability checking and custom exception handling.

## Features

- ✅ Doctor availability checking
- ✅ Appointment booking with confirmation
- ✅ Custom exception handling for unavailable slots
- ✅ Web-based user interface
- ✅ RESTful API backend
- ✅ Real-time availability status

## Project Structure

```
Hospital_management_system/
├── src/com/wipro/hospital/
│   ├── bean/AppointmentBean.java          # Data model for appointments
│   ├── util/DoctorUnavailableException.java # Custom exception class
│   ├── dao/AppointmentDAO.java            # Data access layer
│   └── service/AppointmentService.java    # Business logic layer
├── frontend/
│   ├── index.html                         # Web interface
│   ├── styles.css                         # Styling
│   └── script.js                          # Frontend logic
├── server.js                              # Node.js API server
├── package.json                           # Dependencies
└── README.md                              # This file
```

## Java Backend Classes

### 1. AppointmentBean (com.wipro.hospital.bean)
- Holds appointment information including doctor, patient, and time details
- Contains: appointmentId, doctorId, patientName, appointmentDate, timeSlot

### 2. DoctorUnavailableException (com.wipro.hospital.util)
- Custom exception thrown when doctor is not available
- Implements toString() method for error messaging

### 3. AppointmentDAO (com.wipro.hospital.dao)
- Data access layer with availability checking and booking logic
- Methods: checkAvailability(), bookAppointment()

### 4. AppointmentService (com.wipro.hospital.service)
- Business logic layer with validation and exception handling
- Methods: scheduleAppointment(), main() for testing

## Quick Start

### Running the Java Backend
```bash
# Compile Java classes
javac -d . src/com/wipro/hospital/**/*.java

# Run the service
java com.wipro.hospital.service.AppointmentService
```

### Running the Web Application
```bash
# Install dependencies
npm install

# Start the server
npm start
```

Visit `http://localhost:3000` to access the web interface.

## API Endpoints

- `POST /api/appointments` - Book new appointment
- `GET /api/availability` - Check all doctor availability
- `GET /api/appointments/:doctorId/:timeSlot` - Check specific slot

## Test Cases

### Test Case 1: Available Slot
- **Input**: Doctor ID: D101, Time: 9:00 AM
- **Expected**: Appointment Confirmed: AID123

### Test Case 2: Unavailable Slot
- **Input**: Doctor ID: D101, Time: 10:00 AM (pre-booked)
- **Expected**: DoctorUnavailableException

## Pre-booked Slots (for testing)
- D101 at 10:00 AM
- D102 at 2:00 PM

## Technologies Used
- **Backend**: Java (Core Java concepts)
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **API Server**: Node.js with Express
- **Storage**: In-memory (can be extended to MongoDB)

## Usage Instructions

1. **Web Interface**: Open browser and go to `http://localhost:3000`
2. **Select Doctor**: Choose from available doctors (D101, D102, D103)
3. **Enter Details**: Fill patient name, date, and time slot
4. **Book Appointment**: Click "Book Appointment" button
5. **Check Availability**: Use "View Available Slots" to see current status

The system will automatically handle availability checking and show appropriate success/error messages based on the Java backend logic.
