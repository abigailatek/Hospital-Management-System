package com.hms.tests;

import com.hms.models.Prescription;
import com.hms.services.PrescriptionService;

public class TestPrescription {

    public static void main(String[] args) {

        Prescription prescription = new Prescription();

        prescription.setRecordId(1);
        prescription.setMedicineName("Paracetamol");
        prescription.setDosage("500mg");
        prescription.setDuration("5 Days");
        prescription.setNotes("After meals");

        PrescriptionService service = new PrescriptionService();

        boolean success = service.addPrescription(prescription);

        if (success) {
            System.out.println("Prescription Added Successfully!");
        } else {
            System.out.println("Failed!");
        }
    }
}