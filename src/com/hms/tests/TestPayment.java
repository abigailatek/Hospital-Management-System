package com.hms.tests;

import com.hms.models.Payment;
import com.hms.services.PaymentService;

import java.time.LocalDate;

public class TestPayment {

    public static void main(String[] args) {

        Payment payment = new Payment();

        payment.setBillId(1);
        payment.setAmount(150000);
        payment.setPaymentMethod("Cash");
        payment.setPaymentDate(LocalDate.now());

        PaymentService service = new PaymentService();

        if (service.makePayment(payment)) {
            System.out.println("Payment recorded successfully!");
        } else {
            System.out.println("Failed to record payment.");
        }
    }
}