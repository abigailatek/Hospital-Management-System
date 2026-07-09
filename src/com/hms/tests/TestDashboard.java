package com.hms.tests;

import com.hms.services.DashboardService;

public class TestDashboard {

    public static void main(String[] args) {

        DashboardService service = new DashboardService();

        System.out.println("========== LifeCare Hospital ==========");
        System.out.println();

        System.out.println("Patients      : " + service.getPatientCount());
        System.out.println("Doctors       : " + service.getDoctorCount());
        System.out.println("Appointments  : " + service.getAppointmentCount());
        System.out.println("Bills         : " + service.getBillCount());
        System.out.println("Payments      : " + service.getPaymentCount());
        System.out.println("Laboratory    : " + service.getLabTestCount());
        System.out.println("Inventory     : " + service.getInventoryCount());
        System.out.println("Staff         : " + service.getStaffCount());
    }
}