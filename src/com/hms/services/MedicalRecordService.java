package com.hms.services;

import java.util.List;

import com.hms.dao.MedicalRecordDAO;
import com.hms.models.MedicalRecord;

public class MedicalRecordService {

    private MedicalRecordDAO medicalRecordDAO;

    public MedicalRecordService() {
        medicalRecordDAO = new MedicalRecordDAO();
    }

    public boolean addMedicalRecord(MedicalRecord record) {

        if (record.getPatientId() <= 0) {
            throw new IllegalArgumentException("Invalid Patient ID.");
        }

        if (record.getDoctorId() <= 0) {
            throw new IllegalArgumentException("Invalid Doctor ID.");
        }

        if (record.getDiagnosis() == null ||
            record.getDiagnosis().isBlank()) {

            throw new IllegalArgumentException("Diagnosis is required.");
        }

        if (record.getTreatment() == null ||
            record.getTreatment().isBlank()) {

            throw new IllegalArgumentException("Treatment is required.");
        }

        return medicalRecordDAO.addMedicalRecord(record);
    }
      public List<MedicalRecord>
getAllMedicalRecords() {

    return medicalRecordDAO
            .getAllMedicalRecords();
}

public boolean updateMedicalRecord(
        MedicalRecord record) {

    return medicalRecordDAO
            .updateMedicalRecord(record);
}

public boolean deleteMedicalRecord(
        int id) {

    return medicalRecordDAO
            .deleteMedicalRecord(id);
}

public List<MedicalRecord>
searchMedicalRecords(
        int patientId) {

    return medicalRecordDAO
            .searchMedicalRecords(patientId);
}
}