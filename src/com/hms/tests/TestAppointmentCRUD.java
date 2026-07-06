package com.hms.tests;

import com.hms.models.Appointment;
import com.hms.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestAppointmentCRUD {

    public static void main(String[] args) {

        Appointment appointment = new Appointment();

        // Make sure these IDs already exist in your database
        appointment.setPatientId(1);
        appointment.setDoctorId(1);

        appointment.setAppointmentDate(LocalDate.now().plusDays(1));

        appointment.setAppointmentTime(LocalTime.of(10, 30));

        appointment.setStatus("Scheduled");

        appointment.setReason("General Checkup");

        AppointmentService service = new AppointmentService();

        boolean success = service.addAppointment(appointment);

        if (success) {
            System.out.println("================================");
            System.out.println("Appointment Added Successfully!");
            System.out.println("================================");
        } else {
            System.out.println("Failed to Add Appointment!");
        }
    }
}