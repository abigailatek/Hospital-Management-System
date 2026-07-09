package com.hms.services;

import com.hms.dao.DashboardDAO;

public class DashboardService {

    private final DashboardDAO dao = new DashboardDAO();

    public int getPatientCount() {
        return dao.getPatientCount();
    }

    public int getDoctorCount() {
        return dao.getDoctorCount();
    }

    public int getAppointmentCount() {
        return dao.getAppointmentCount();
    }

    public int getBillCount() {
        return dao.getBillCount();
    }

    public int getLabTestCount() {
        return dao.getLabTestCount();
    }

    public int getStaffCount() {
        return dao.getStaffCount();
    }

    public int getInventoryCount() {
        return dao.getInventoryCount();
    }

    public int getPaymentCount() {
        return dao.getPaymentCount();
    }
}