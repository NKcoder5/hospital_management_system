package com.wipro.hospital.dao;

import com.wipro.hospital.bean.AppointmentBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AppointmentDAO {
    // Simulating a database with in-memory storage
    private static Map<String, String> bookedSlots = new HashMap<>();
    private static Random random = new Random();
    
    static {
        // Pre-populate some booked slots for testing (with dates)
        bookedSlots.put("D101_2024-01-15_10:00 AM", "AID001");
        bookedSlots.put("D102_2024-01-15_2:00 PM", "AID002");
    }
    
    public boolean checkAvailability(String doctorId, String appointmentDate, String timeSlot) {
        String key = doctorId + "_" + appointmentDate + "_" + timeSlot;
        return !bookedSlots.containsKey(key);
    }
    
    public String bookAppointment(AppointmentBean bean) {
        String key = bean.getDoctorId() + "_" + bean.getAppointmentDate() + "_" + bean.getTimeSlot();
        
        if (checkAvailability(bean.getDoctorId(), bean.getAppointmentDate(), bean.getTimeSlot())) {
            // Generate appointment ID
            String appointmentId = "AID" + String.format("%03d", random.nextInt(1000));
            bean.setAppointmentId(appointmentId);
            
            // Book the slot
            bookedSlots.put(key, appointmentId);
            
            return "Appointment Confirmed: " + appointmentId;
        } else {
            return null; // Slot not available
        }
    }
    
    // Method to get all booked slots (for testing purposes)
    public Map<String, String> getBookedSlots() {
        return new HashMap<>(bookedSlots);
    }
}
