package com.hms.utils;

import com.hms.exceptions.ValidationException;
import com.hms.models.Patient;

public class PatientValidator {

    public static void validate(Patient patient)
            throws ValidationException {

        if (!ValidationUtils.isValidName(patient.getFirstName()))
            throw new ValidationException("Invalid first name.");

        if (!ValidationUtils.isValidName(patient.getLastName()))
            throw new ValidationException("Invalid last name.");

        if (!ValidationUtils.isValidEmail(patient.getEmail()))
            throw new ValidationException("Invalid email.");

        if (!ValidationUtils.isValidPhone(patient.getPhone()))
            throw new ValidationException("Invalid phone.");

        if (!ValidationUtils.isValidDateOfBirth(patient.getDateOfBirth()))
            throw new ValidationException("Invalid date of birth.");
    }
}