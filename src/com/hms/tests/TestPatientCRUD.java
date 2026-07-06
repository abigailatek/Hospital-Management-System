package com.hms.tests;

import com.hms.exceptions.ValidationException;
import com.hms.models.Patient;
import com.hms.services.PatientService;

import java.time.LocalDate;

public class TestPatientCRUD {

    public static void main(String[] args) {

        PatientService service = new PatientService();

        Patient patient = new Patient();

        patient.setFirstName("Precious");
        patient.setLastName("Nuwampa");
        patient.setGender("Female");
        patient.setDateOfBirth(LocalDate.of(1999, 5, 10));
        patient.setPhone("0701234567");
        patient.setEmail("precious@example.com");
        patient.setAddress("Kampala");
        patient.setEmergencyContact("Precious's Mom: 0707654321");
 try {
    boolean success = service.addPatient(patient);

    if (success) {
        System.out.println("Patient Added Successfully!");
    } else {
        System.out.println("Failed to Add Patient!");
    }

 } catch (final ValidationException e) {
    System.out.println(e.getMessage());
  }
 }
}
