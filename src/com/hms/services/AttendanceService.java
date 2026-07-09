package com.hms.services;

import com.hms.dao.AttendanceDAO;
import com.hms.models.Attendance;

public class AttendanceService {

    private final AttendanceDAO dao = new AttendanceDAO();

    public boolean recordAttendance(Attendance attendance) {

        if (attendance.getStaffId() <= 0) {
            throw new IllegalArgumentException("Invalid Staff ID.");
        }

        if (attendance.getStatus() == null || attendance.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }

        return dao.addAttendance(attendance);
    }
}