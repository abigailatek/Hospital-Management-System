package com.hms.services;

import com.hms.dao.ReportDAO;

import javax.swing.table.DefaultTableModel;

public class ReportService {

    private final ReportDAO dao = new ReportDAO();

    public DefaultTableModel getPatientReport() {
        return dao.getPatientReport();
    }
}