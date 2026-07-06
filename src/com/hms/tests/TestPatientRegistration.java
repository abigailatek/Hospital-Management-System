package com.hms.tests;

import com.hms.models.Patient;

public class TestPatientRegistration {
    Patient patient = new Patient();


        patient.setFirstName("Precious");
        patient.setLastName("Nuwampa");
        patient.setGender("Female");
        patient.setDateOfBirth(LocalDate.of(1999, 5, 10));
        patient.setPhone("0701234567");
        patient.setEmail("precious@example.com");
        patient.setAddress("Kampala");
        patient.setEmergencyContact("Precious's Mom: 0707654321");
}
