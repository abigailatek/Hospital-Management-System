package com.hms.tests;

import com.hms.models.Staff;
import com.hms.services.StaffService;

import java.math.BigDecimal;

public class TestStaff {

    public static void main(String[] args) {

        Staff staff = new Staff();

        staff.setUserId(1); // Existing UserID
        staff.setFirstName("Timothy");
        staff.setLastName("Nuwagaba");
        staff.setPosition("System Administrator");
        staff.setPhone("0701234567");
        staff.setEmail("admin@lifecarehospital.com");
        staff.setSalary(new BigDecimal("3500000"));

        StaffService service = new StaffService();

        if (service.addStaff(staff)) {
            System.out.println("Staff added successfully!");
        } else {
            System.out.println("Failed to add staff.");
        }
    }
}