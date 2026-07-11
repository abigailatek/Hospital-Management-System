package com.hms.services;

import com.hms.dao.PaymentDAO;
import com.hms.models.Payment;

import java.util.List;

public class PaymentService {

    private final PaymentDAO paymentDAO =
            new PaymentDAO();

    public boolean makePayment(Payment payment) {

        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero.");
        }

        return paymentDAO.addPayment(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public boolean deletePayment(int id) {
        return paymentDAO.deletePayment(id);
    }

    public List<Payment> searchPayments(int billId) {
        return paymentDAO.searchPayments(billId);
    }
}