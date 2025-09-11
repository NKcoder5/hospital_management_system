package com.wipro.hospital.bean;

public class AppointmentBean {
    private String appointmentId;
    private String doctorId;
    private String patientName;
    private String appointmentDate;
    private String timeSlot;
    
    // Default constructor
    public AppointmentBean() {}
    
    // Parameterized constructor
    public AppointmentBean(String appointmentId, String doctorId, String patientName, 
                          String appointmentDate, String timeSlot) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
    }
    
    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }
    
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public String getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public String getAppointmentDate() {
        return appointmentDate;
    }
    
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public String getTimeSlot() {
        return timeSlot;
    }
    
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
    
    @Override
    public String toString() {
        return "AppointmentBean{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}
