package com.hms.services;

import com.hms.dao.PrescriptionDAO;
import com.hms.models.Prescription;

public class PrescriptionService {

    private PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {
        prescriptionDAO = new PrescriptionDAO();
    }

    public boolean addPrescription(Prescription prescription) {

        if (prescription.getRecordId() <= 0) {
            throw new IllegalArgumentException("Invalid Record ID.");
        }

        if (prescription.getMedicineName() == null ||
            prescription.getMedicineName().isBlank()) {
            throw new IllegalArgumentException("Medicine name is required.");
        }

        return prescriptionDAO.addPrescription(prescription);
    }
}