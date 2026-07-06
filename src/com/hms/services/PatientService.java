package com.hms.services;

import com.hms.dao.PatientDAO;
import com.hms.exceptions.ValidationException;
import com.hms.models.Patient;
import com.hms.utils.ValidationUtils;

import java.util.List;

public class PatientService {

    private PatientDAO patientDAO;

    public PatientService() {
        patientDAO = new PatientDAO();
    }

    public boolean addPatient(Patient patient) throws ValidationException {

        if (!ValidationUtils.isValidName(patient.getFirstName())) {
            throw new ValidationException("First name is required.");
        }

        if (!ValidationUtils.isValidName(patient.getLastName())) {
            throw new ValidationException("Last name is required.");
        }

        if (!ValidationUtils.isValidEmail(patient.getEmail())) {
            throw new ValidationException("Invalid email address.");
        }

        if (!ValidationUtils.isValidPhone(patient.getPhone())) {
            throw new ValidationException("Invalid phone number.");
        }

        if (!ValidationUtils.isValidDateOfBirth(patient.getDateOfBirth())) {
            throw new ValidationException("Invalid date of birth.");
        }

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