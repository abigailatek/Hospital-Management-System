package com.hms.services;

import com.hms.dao.PrescriptionDAO;
import com.hms.models.Prescription;

import java.util.List;

public class PrescriptionService {

    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    public boolean addPrescription(Prescription prescription) {

        if (prescription.getMedicineName() == null ||
                prescription.getMedicineName().isBlank()) {

            throw new IllegalArgumentException("Medicine name is required.");
        }

        return prescriptionDAO.addPrescription(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    public boolean deletePrescription(int id) {
        return prescriptionDAO.deletePrescription(id);
    }

    public List<Prescription> searchPrescriptions(int recordId) {
        return prescriptionDAO.searchPrescriptions(recordId);
    }
}