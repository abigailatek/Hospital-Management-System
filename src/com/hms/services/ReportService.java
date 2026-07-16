package com.hms.services;

import com.hms.dao.ReportDAO;
import com.hms.dao.PatientDAO;
import com.hms.dao.DoctorDAO;
import com.hms.dao.AppointmentDAO;
import com.hms.dao.LabTestDAO;
import com.hms.dao.PaymentDAO;
import java.math.BigDecimal;

import javax.swing.table.DefaultTableModel;

public class ReportService {

    private final ReportDAO dao = new ReportDAO();

    public DefaultTableModel getPatientReport() {
        return dao.getReport(
                "SELECT * FROM Patients ORDER BY PatientID");
    }

    public DefaultTableModel getDoctorReport() {
        return dao.getReport(
                "SELECT * FROM Doctors ORDER BY DoctorID");
    }

    public DefaultTableModel getAppointmentReport() {
        return dao.getReport(
                "SELECT * FROM Appointments ORDER BY AppointmentID");
    }

    public DefaultTableModel getBillingReport() {
        return dao.getReport(
                "SELECT * FROM Bills ORDER BY BillID");
    }

    public DefaultTableModel getPaymentReport() {
        return dao.getReport(
                "SELECT * FROM Payments ORDER BY PaymentID");
    }

    public DefaultTableModel getInventoryReport() {
        return dao.getReport(
                "SELECT * FROM Inventory ORDER BY ItemID");
    }

    public DefaultTableModel getStaffReport() {
        return dao.getReport(
                "SELECT * FROM Staff ORDER BY StaffID");
    }

    public DefaultTableModel getLabReport() {
        return dao.getReport(
                "SELECT * FROM LabTests ORDER BY TestID");
    }
      
public int getPatientCount() {
    return new PatientDAO().getPatientCount();
}

public int getDoctorCount() {
    return new DoctorDAO().getDoctorCount();
}

public int getAppointmentCount() {
    return new AppointmentDAO().getAppointmentCount();
}

public int getLabTestCount() {
    return new LabTestDAO().getLabTestCount();
}

public BigDecimal getTotalRevenue() {
    return new PaymentDAO().getTotalRevenue();
}
}