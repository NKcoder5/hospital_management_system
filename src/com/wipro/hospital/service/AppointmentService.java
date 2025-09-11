package com.wipro.hospital.service;

import com.wipro.hospital.bean.AppointmentBean;
import com.wipro.hospital.dao.AppointmentDAO;
import com.wipro.hospital.util.DoctorUnavailableException;
import java.util.Scanner;

public class AppointmentService {
    private AppointmentDAO appointmentDAO;
    
    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }
    
    public String scheduleAppointment(AppointmentBean bean) throws DoctorUnavailableException {
        // Validate input data
        if (bean.getDoctorId() == null || bean.getDoctorId().trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor ID cannot be null or empty");
        }
        if (bean.getTimeSlot() == null || bean.getTimeSlot().trim().isEmpty()) {
            throw new IllegalArgumentException("Time slot cannot be null or empty");
        }
        if (bean.getPatientName() == null || bean.getPatientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be null or empty");
        }
        
        // Check doctor availability
        if (!appointmentDAO.checkAvailability(bean.getDoctorId(), bean.getAppointmentDate(), bean.getTimeSlot())) {
            throw new DoctorUnavailableException("Doctor " + bean.getDoctorId() + 
                " is not available on " + bean.getAppointmentDate() + " at " + bean.getTimeSlot());
        }
        
        // Book appointment
        String result = appointmentDAO.bookAppointment(bean);
        if (result != null) {
            return result;
        } else {
            throw new DoctorUnavailableException("Failed to book appointment");
        }
    }
    
    public static void main(String[] args) {
        AppointmentService service = new AppointmentService();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Hospital Appointment Booking System ===");
        
        try {
            // Test Case 1: Available slot
            System.out.println("\n--- Test Case 1: Available Slot ---");
            AppointmentBean appointment1 = new AppointmentBean();
            appointment1.setDoctorId("D101");
            appointment1.setTimeSlot("9:00 AM");
            appointment1.setPatientName("John Doe");
            appointment1.setAppointmentDate("2024-01-15");
            
            String result1 = service.scheduleAppointment(appointment1);
            System.out.println("Result: " + result1);
            
            // Test Case 2: Already booked slot (same date)
            System.out.println("\n--- Test Case 2: Already Booked Slot (Same Date) ---");
            AppointmentBean appointment2 = new AppointmentBean();
            appointment2.setDoctorId("D101");
            appointment2.setTimeSlot("10:00 AM");
            appointment2.setPatientName("Jane Smith");
            appointment2.setAppointmentDate("2024-01-15");
            
            String result2 = service.scheduleAppointment(appointment2);
            System.out.println("Result: " + result2);
            
            // Test Case 3: Same doctor, same time, different date (should work)
            System.out.println("\n--- Test Case 3: Same Time, Different Date ---");
            AppointmentBean appointment3 = new AppointmentBean();
            appointment3.setDoctorId("D101");
            appointment3.setTimeSlot("10:00 AM");
            appointment3.setPatientName("Bob Wilson");
            appointment3.setAppointmentDate("2024-01-16");
            
            String result3 = service.scheduleAppointment(appointment3);
            System.out.println("Result: " + result3);
            
        } catch (DoctorUnavailableException e) {
            System.out.println("Exception: " + e.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Interactive booking
        System.out.println("\n--- Interactive Booking ---");
        System.out.println("Enter appointment details:");
        
        try {
            System.out.print("Doctor ID: ");
            String doctorId = scanner.nextLine();
            
            System.out.print("Patient Name: ");
            String patientName = scanner.nextLine();
            
            System.out.print("Appointment Date (YYYY-MM-DD): ");
            String appointmentDate = scanner.nextLine();
            
            System.out.print("Time Slot (e.g., 10:00 AM): ");
            String timeSlot = scanner.nextLine();
            
            AppointmentBean appointment = new AppointmentBean();
            appointment.setDoctorId(doctorId);
            appointment.setPatientName(patientName);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setTimeSlot(timeSlot);
            
            String result = service.scheduleAppointment(appointment);
            System.out.println("\nSuccess: " + result);
            
        } catch (DoctorUnavailableException e) {
            System.out.println("\nBooking Failed: " + e.toString());
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
