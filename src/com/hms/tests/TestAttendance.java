package com.hms.tests;

import com.hms.models.Attendance;
import com.hms.services.AttendanceService;

import java.time.LocalDate;

public class TestAttendance {

    public static void main(String[] args) {

        Attendance attendance = new Attendance();

        attendance.setStaffId(1);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setStatus("Present");

        AttendanceService service = new AttendanceService();

        if (service.recordAttendance(attendance)) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Failed to record attendance.");
        }
    }
}