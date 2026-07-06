package com.hms.tests;

import com.hms.exceptions.ValidationException;
import com.hms.models.Patient;
import com.hms.services.PatientService;

import java.time.LocalDate;

public class TestPatientRegistration {

    public static void main(String[] args) {

        Patient patient = new Patient();

        patient.setFirstName("Precious");
        patient.setLastName("Nuwampa");
        patient.setGender("Female");
        patient.setDateOfBirth(LocalDate.of(1999, 5, 10));
        patient.setPhone("0701234567");
        patient.setEmail("precious@example.com");
        patient.setAddress("Kampala");
        patient.setEmergencyContact("Precious's Mom: 0707654321");

        PatientService service = new PatientService();

        try {
            boolean success = service.addPatient(patient);
            if (success) {
                System.out.println("Patient Registered Successfully!") ;
            } else {
                System.out.println("Failed to register patient.");
            }
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}