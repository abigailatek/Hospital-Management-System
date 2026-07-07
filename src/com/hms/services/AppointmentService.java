package com.hms.services;

import com.hms.dao.AppointmentDAO;
import com.hms.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }

    public boolean addAppointment(Appointment appointment) {

        // Rule 1: Appointment date cannot be in the past
        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            System.out.println("Appointment cannot be scheduled in the past.");
            return false;
        }
if (!appointmentDAO.isDoctorAvailable(
        appointment.getDoctorId(),
        appointment.getAppointmentDate(),
        appointment.getAppointmentTime())) {

    System.out.println("Doctor is already booked at that time.");
    return false;
}if (!appointmentDAO.isDoctorAvailable(
        appointment.getDoctorId(),
        appointment.getAppointmentDate(),
        appointment.getAppointmentTime())) {

    System.out.println("--------------------------------");
    System.out.println("Doctor is already booked!");
    System.out.println("--------------------------------");

    return false;
}

return appointmentDAO.addAppointment(appointment);
        
    }

    public Appointment getAppointmentById(int id) {
        return appointmentDAO.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    public boolean updateAppointment(Appointment appointment) {
        return appointmentDAO.updateAppointment(appointment);
    }

    public boolean deleteAppointment(int id) {
        return appointmentDAO.deleteAppointment(id);
    }

    public List<Appointment> searchAppointments(int doctorId) {
        return appointmentDAO.searchAppointments(doctorId);
    }
}