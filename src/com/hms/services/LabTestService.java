package com.hms.services;

import java.util.List;

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
     public List<LabTest> getAllLabTests() {
    return dao.getAllLabTests();
}

public boolean deleteLabTest(int id) {
    return dao.deleteLabTest(id);
}

public List<LabTest> searchLabTests(int patientId) {
    return dao.searchLabTests(patientId);
}
    
}