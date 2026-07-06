package com.hms.tests;

import com.hms.models.Appointment;
import com.hms.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestAppointmentService {

    public static void main(String[] args) {

        Appointment appointment = new Appointment();

        appointment.setPatientId(1);
        appointment.setDoctorId(1);

        appointment.setAppointmentDate(LocalDate.now().plusDays(2));
        appointment.setAppointmentTime(LocalTime.of(9, 0));

        appointment.setStatus("Scheduled");
        appointment.setReason("Routine Checkup");

        AppointmentService service = new AppointmentService();

        if (service.addAppointment(appointment)) {
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Appointment booking failed.");
        }
    }
}