package com.hms.services;

import com.hms.dao.DoctorDAO;
import com.hms.models.Doctor;

import java.util.List;

public class DoctorService {

    private final DoctorDAO dao =
            new DoctorDAO();

    public boolean createDoctor(
            Doctor doctor) {

        return dao.addDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return dao.getAllDoctors();
    }

    public boolean deleteDoctor(
            int doctorID) {

        return dao.deleteDoctor(doctorID);
    }
}