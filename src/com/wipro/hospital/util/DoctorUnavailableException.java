package com.wipro.hospital.util;

public class DoctorUnavailableException extends Exception {
    private String message;
    
    public DoctorUnavailableException() {
        this.message = "Doctor is not available at the requested time slot";
    }
    
    public DoctorUnavailableException(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "DoctorUnavailableException: " + message;
    }
}
