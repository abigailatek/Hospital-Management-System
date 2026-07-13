package com.hms.services;

import com.hms.dao.PrescriptionDAO;
import com.hms.models.Prescription;

import java.util.List;

public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {
        prescriptionDAO = new PrescriptionDAO();
    }

    public boolean addPrescription(Prescription prescription) {

        if (prescription.getRecordId() <= 0) {
            throw new IllegalArgumentException("Invalid Record ID.");
        }

        if (prescription.getMedicineName() == null
                || prescription.getMedicineName().isBlank()) {
            throw new IllegalArgumentException(
                    "Medicine name is required.");
        }

        if (prescription.getDosage() == null
                || prescription.getDosage().isBlank()) {
            throw new IllegalArgumentException(
                    "Dosage is required.");
        }

        if (prescription.getDuration() == null
                || prescription.getDuration().isBlank()) {
            throw new IllegalArgumentException(
                    "Duration is required.");
        }

        return prescriptionDAO.addPrescription(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    public boolean deletePrescription(int id) {
        return prescriptionDAO.deletePrescription(id);
    }

    public List<Prescription> searchPrescriptions(
            int recordId) {
        return prescriptionDAO.searchPrescriptions(recordId);
    }
}