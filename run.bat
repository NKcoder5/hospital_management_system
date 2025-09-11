@echo off
echo Compiling Hospital Management System...
javac -cp src src/com/wipro/hospital/bean/AppointmentBean.java
javac -cp src src/com/wipro/hospital/util/DoctorUnavailableException.java
javac -cp src src/com/wipro/hospital/dao/AppointmentDAO.java
javac -cp src src/com/wipro/hospital/service/AppointmentService.java
echo.
echo Compilation completed successfully!
echo.
echo Running the application...
echo.
java -cp src com.wipro.hospital.service.AppointmentService
pause
