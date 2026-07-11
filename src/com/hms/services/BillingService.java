package com.hms.services;

import com.hms.dao.BillingDAO;
import com.hms.models.Billing;

import java.util.List;

public class BillingService {

    private BillingDAO dao = new BillingDAO();

    public boolean createBill(Billing bill) {

        if (bill.getTotalAmount().doubleValue() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero.");
        }

        if (bill.getPaymentStatus() == null ||
                bill.getPaymentStatus().isBlank()) {

            throw new IllegalArgumentException(
                    "Payment status is required.");
        }

        return dao.addBill(bill);
    }

    public List<Billing> getAllBills() {
        return dao.getAllBills();
    }

    public boolean deleteBill(int id) {
        return dao.deleteBill(id);
    }

    public List<Billing> searchBills(int patientId) {
        return dao.searchBills(patientId);
    }
}