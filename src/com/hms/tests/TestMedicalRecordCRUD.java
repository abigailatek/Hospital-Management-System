package com.hms.tests;

import java.time.LocalDate;

import com.hms.models.MedicalRecord;
import com.hms.services.MedicalRecordService;

public class TestMedicalRecordCRUD {

    public static void main(String[] args) {

        MedicalRecord record = new MedicalRecord();

        record.setPatientId(1);
        record.setDoctorId(1);
        record.setDiagnosis("Malaria");
        record.setTreatment("Artemether Lumefantrine");
        record.setAllergies("None");
        record.setChronicConditions("None");
        record.setRecordDate(LocalDate.now());

        MedicalRecordService service = new MedicalRecordService();

        boolean success = service.addMedicalRecord(record);

        if (success) {
            System.out.println("Medical Record Saved Successfully!");
        } else {
            System.out.println("Failed to Save Medical Record!");
        }
    }
}