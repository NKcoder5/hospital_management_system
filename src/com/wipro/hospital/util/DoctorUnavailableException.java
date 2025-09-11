package com.wipro.hospital.util;

public class DoctorUnavailableException extends Exception {
    public String toString() {
        return "DoctorUnavailableException: The doctor is not available at the selected time slot.";
    }
}
