# Hospital Management System

A simple Java-based Hospital Management System that allows scheduling appointments with doctors.

## Project Structure

```
src/
├── com/wipro/hospital/
│   ├── bean/
│   │   └── AppointmentBean.java          # Data model for appointments
│   ├── dao/
│   │   └── AppointmentDAO.java           # Data access layer
│   ├── service/
│   │   └── AppointmentService.java       # Business logic and main application
│   └── util/
│       └── DoctorUnavailableException.java # Custom exception class
```

## Prerequisites

- Java JDK 8 or higher
- Windows PowerShell or Command Prompt

## How to Run

### Method 1: Using the batch file (Recommended for Windows)
1. Double-click on `run.bat` file
2. The application will compile and run automatically

### Method 2: Manual compilation and execution

#### Step 1: Compile all Java files
```bash
javac -cp src src/com/wipro/hospital/bean/AppointmentBean.java
javac -cp src src/com/wipro/hospital/util/DoctorUnavailableException.java
javac -cp src src/com/wipro/hospital/dao/AppointmentDAO.java
javac -cp src src/com/wipro/hospital/service/AppointmentService.java
```

#### Step 2: Run the application
```bash
java -cp src com.wipro.hospital.service.AppointmentService
```

## How to Use

1. **Enter Doctor ID**: Provide a unique identifier for the doctor
2. **Enter Patient Name**: Enter the name of the patient
3. **Enter Appointment Date**: Use format yyyy-mm-dd (e.g., 2024-01-15)
4. **Enter Time Slot**: Specify the time (e.g., 10:00 AM)

The system will:
- Check if the doctor is available at the specified time slot
- Book the appointment if available
- Generate a unique appointment ID
- Handle cases where the doctor is unavailable

## Features

- **Appointment Scheduling**: Book appointments with doctors
- **Availability Check**: Verify doctor availability before booking
- **Exception Handling**: Custom exception for unavailable doctors
- **Data Persistence**: In-memory storage using HashMap
- **Input Validation**: User-friendly console interface

## Error Resolution

All compilation errors have been resolved:
- ✅ Proper package structure
- ✅ Correct import statements
- ✅ Valid Java syntax
- ✅ Proper classpath configuration

## Sample Output

```
Enter Doctor ID: D001
Enter Patient Name: John Doe
Enter Appointment Date (yyyy-mm-dd): 2024-01-15
Enter Time Slot (e.g., 10:00 AM): 10:00 AM
Appointment Confirmed: AID1
```

The project is now error-free and ready to run!
