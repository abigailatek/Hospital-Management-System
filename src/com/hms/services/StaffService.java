package com.hms.services;

import com.hms.dao.StaffDAO;
import com.hms.models.Staff;

public class StaffService {

    private final StaffDAO dao = new StaffDAO();

    public boolean addStaff(Staff staff) {

        if (staff.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid User ID.");
        }

        if (staff.getFirstName() == null || staff.getFirstName().isBlank()) {
            throw new IllegalArgumentException("First name is required.");
        }

        if (staff.getLastName() == null || staff.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name is required.");
        }

        if (staff.getPosition() == null || staff.getPosition().isBlank()) {
            throw new IllegalArgumentException("Position is required.");
        }

        return dao.addStaff(staff);
    }
}