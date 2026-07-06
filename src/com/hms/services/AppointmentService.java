package com.hms.services;

import com.hms.dao.AppointmentDAO;
import com.hms.models.Appointment;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }
    public boolean addAppointment(Appointment appointment) {
        return appointmentDAO.addAppointment(appointment);
    }
}