package com.hms.services;

import com.hms.dao.LabTestDAO;
import com.hms.models.LabTest;

public class LabTestService {

    private final LabTestDAO dao = new LabTestDAO();

    public boolean createLabTest(LabTest test) {

        if (test.getTestName() == null || test.getTestName().isBlank()) {
            throw new IllegalArgumentException("Test name cannot be empty.");
        }

        return dao.addLabTest(test);
    }
}