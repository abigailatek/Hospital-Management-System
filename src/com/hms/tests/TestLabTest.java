package com.hms.tests;

import com.hms.models.LabTest;
import com.hms.services.LabTestService;

import java.time.LocalDate;

public class TestLabTest {

    public static void main(String[] args) {

        LabTest test = new LabTest();

        test.setPatientId(1);
        test.setDoctorId(1);
        test.setTestName("Complete Blood Count");
        test.setResult("Normal");
        test.setTestDate(LocalDate.now());
        test.setStatus("Completed");

        LabTestService service = new LabTestService();

        if (service.createLabTest(test)) {
            System.out.println("Lab Test added successfully!");
        } else {
            System.out.println("Failed to add Lab Test.");
        }
    }
}