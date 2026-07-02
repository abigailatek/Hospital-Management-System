package com.hms.services;

import com.hms.models.Patient;
import java.util.ArrayList;

public class PatientService {

    private static ArrayList<Patient> patients = new ArrayList<>();

    public static void addPatient(Patient p) {
        patients.add(p);
    }

    public static void deletePatient(String name) {
        patients.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public static ArrayList<Patient> getPatients() {
        return patients;
    }

    public static ArrayList<Patient> searchPatient(String keyword) {
        ArrayList<Patient> result = new ArrayList<>();

        for (Patient p : patients) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}