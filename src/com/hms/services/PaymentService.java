package com.hms.services;

import com.hms.dao.PaymentDAO;
import com.hms.models.Payment;

public class PaymentService {

    private final PaymentDAO paymentDAO = new PaymentDAO();

    public boolean makePayment(Payment payment) {

        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }

        return paymentDAO.addPayment(payment);
    }
}