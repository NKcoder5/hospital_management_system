package com.wipro.hospital.dao;

import java.util.HashMap;
import com.wipro.hospital.bean.AppointmentBean;

public class AppointmentDAO {
    private static HashMap<String, String> bookingData = new HashMap<>();

    public boolean checkAvailability(String doctorId, String timeSlot) {
        String key = doctorId + timeSlot;
        return !bookingData.containsKey(key);
    }

    public String bookAppointment(AppointmentBean bean) {
        String key = bean.getDoctorId() + bean.getTimeSlot();
        String appointmentId = "AID" + (bookingData.size() + 1);
        bookingData.put(key, appointmentId);
        return appointmentId;
    }
}
