package com.hms.services;

import com.hms.models.Doctor;
import java.util.ArrayList;

public class DoctorService {

    private static ArrayList<Doctor> doctors = new ArrayList<>();

    public static void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public static void deleteDoctor(String name) {
        doctors.removeIf(d -> d.getName().equalsIgnoreCase(name));
    }

    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }
}