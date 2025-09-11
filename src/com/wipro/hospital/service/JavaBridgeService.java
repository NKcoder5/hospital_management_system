package com.wipro.hospital.service;

import com.wipro.hospital.bean.AppointmentBean;
import com.wipro.hospital.dao.AppointmentDAO;
import com.wipro.hospital.util.DoctorUnavailableException;
import java.util.Map;

public class JavaBridgeService {
    private static AppointmentDAO appointmentDAO = new AppointmentDAO();
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: JavaBridgeService <command> [args...]");
            System.exit(1);
        }
        
        String command = args[0];
        
        try {
            switch (command) {
                case "book":
                    if (args.length != 5) {
                        System.err.println("Usage: JavaBridgeService book <doctorId> <patientName> <appointmentDate> <timeSlot>");
                        System.exit(1);
                    }
                    bookAppointment(args[1], args[2], args[3], args[4]);
                    break;
                    
                case "check":
                    if (args.length != 4) {
                        System.err.println("Usage: JavaBridgeService check <doctorId> <appointmentDate> <timeSlot>");
                        System.exit(1);
                    }
                    checkAvailability(args[1], args[2], args[3]);
                    break;
                    
                case "availability":
                    if (args.length != 2) {
                        System.err.println("Usage: JavaBridgeService availability <appointmentDate>");
                        System.exit(1);
                    }
                    getAllAvailability(args[1]);
                    break;
                    
                default:
                    System.err.println("Unknown command: " + command);
                    System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void bookAppointment(String doctorId, String patientName, String appointmentDate, String timeSlot) {
        try {
            AppointmentService service = new AppointmentService();
            AppointmentBean bean = new AppointmentBean();
            bean.setDoctorId(doctorId);
            bean.setPatientName(patientName);
            bean.setAppointmentDate(appointmentDate);
            bean.setTimeSlot(timeSlot);
            
            String result = service.scheduleAppointment(bean);
            
            // Output JSON for Node.js to parse
            System.out.println("{\"success\": true, \"message\": \"" + result + "\", \"appointmentId\": \"" + bean.getAppointmentId() + "\"}");
            
        } catch (DoctorUnavailableException e) {
            System.out.println("{\"success\": false, \"message\": \"" + e.toString() + "\"}");
        } catch (Exception e) {
            System.err.println("Booking error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void checkAvailability(String doctorId, String appointmentDate, String timeSlot) {
        try {
            boolean available = appointmentDAO.checkAvailability(doctorId, appointmentDate, timeSlot);
            System.out.println("{\"available\": " + available + "}");
        } catch (Exception e) {
            System.err.println("Availability check error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void getAllAvailability(String appointmentDate) {
        try {
            String[] doctors = {"D101", "D102", "D103"};
            String[] timeSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM", "4:00 PM"};
            
            StringBuilder json = new StringBuilder("{\"availability\": {");
            
            for (int i = 0; i < doctors.length; i++) {
                if (i > 0) json.append(", ");
                json.append("\"").append(doctors[i]).append("\": {");
                
                for (int j = 0; j < timeSlots.length; j++) {
                    if (j > 0) json.append(", ");
                    boolean available = appointmentDAO.checkAvailability(doctors[i], appointmentDate, timeSlots[j]);
                    json.append("\"").append(timeSlots[j]).append("\": ").append(available);
                }
                json.append("}");
            }
            
            json.append("}, \"bookedSlots\": [");
            Map<String, String> bookedSlots = appointmentDAO.getBookedSlots();
            int count = 0;
            for (String key : bookedSlots.keySet()) {
                if (count > 0) json.append(", ");
                json.append("\"").append(key).append("\"");
                count++;
            }
            json.append("]}");
            
            System.out.println(json.toString());
        } catch (Exception e) {
            System.err.println("Get availability error: " + e.getMessage());
            System.exit(1);
        }
    }
}
