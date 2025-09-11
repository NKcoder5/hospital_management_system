package com.wipro.hospital.service;

import com.wipro.hospital.bean.AppointmentBean;
import com.wipro.hospital.dao.AppointmentDAO;
import com.wipro.hospital.util.DoctorUnavailableException;
import java.util.Scanner;

public class AppointmentService {
    public String scheduleAppointment(AppointmentBean bean) throws DoctorUnavailableException {
        AppointmentDAO dao = new AppointmentDAO();
        if (dao.checkAvailability(bean.getDoctorId(), bean.getTimeSlot())) {
            return dao.bookAppointment(bean);
        } else {
            throw new DoctorUnavailableException();
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AppointmentService service = new AppointmentService();

        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String patientName = sc.nextLine();

        System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
        String appointmentDate = sc.nextLine();

        System.out.print("Enter Time Slot (e.g., 10:00 AM): ");
        String timeSlot = sc.nextLine();

        AppointmentBean bean = new AppointmentBean();
        bean.setDoctorId(doctorId);
        bean.setPatientName(patientName);
        bean.setAppointmentDate(appointmentDate);
        bean.setTimeSlot(timeSlot);

        try {
            String appointmentId = service.scheduleAppointment(bean);
            System.out.println("Appointment Confirmed: " + appointmentId);
        } catch (DoctorUnavailableException e) {
            System.out.println(e);
        }

        sc.close();
    }
}
