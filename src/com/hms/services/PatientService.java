package com.hms.services;

import com.hms.dao.PatientDAO;
import com.hms.models.Patient;

import java.util.List;

public class PatientService {

    private PatientDAO patientDAO;

    public PatientService() {
        patientDAO = new PatientDAO();
    }

    public boolean addPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    public Patient getPatientById(int patientId) {
        return patientDAO.getPatientById(patientId);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public boolean updatePatient(Patient patient) {
        return patientDAO.updatePatient(patient);
    }

    public boolean deletePatient(int patientId) {
        return patientDAO.deletePatient(patientId);
    }

    public List<Patient> searchPatients(String keyword) {
        return patientDAO.searchPatients(keyword);
    }
}