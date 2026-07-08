package com.hms.tests;

import com.hms.models.Billing;
import com.hms.services.BillingService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestBilling {

    public static void main(String[] args) {

        Billing bill = new Billing();

        bill.setPatientId(1);
        bill.setBillDate(LocalDate.now());
        bill.setTotalAmount(new BigDecimal("150000"));
        bill.setPaymentStatus("Pending");

        BillingService service = new BillingService();

        if (service.createBill(bill)) {

            System.out.println("Bill created successfully!");

        } else {

            System.out.println("Failed to create bill.");
        }

    }

}