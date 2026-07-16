package com.hms.ui;

import com.hms.dao.AppointmentDAO;
import com.hms.dao.LabTestDAO;
import com.hms.models.Appointment;
import com.hms.models.Inventory;
import com.hms.models.LabTest;
import com.hms.services.InventoryService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NotificationPanel extends JPanel {

    private JTextArea notifications;

    public NotificationPanel() {

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,20,20,20));

        JLabel title =
                new JLabel("NOTIFICATIONS");

        title.setFont(Theme.TITLE);
        title.setForeground(
                Theme.PRIMARY_GREEN);

        notifications =
                new JTextArea();

        notifications.setEditable(false);

        notifications.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        notifications.setBackground(
                Color.WHITE);

        add(title, BorderLayout.NORTH);
        add(
                new JScrollPane(
                        notifications),
                BorderLayout.CENTER);

        loadNotifications();
    }

    private void loadNotifications() {

        notifications.setText("");

      InventoryService inventoryService =
        new InventoryService();

for (Inventory i :
        inventoryService.getLowStockItems()) {

    notifications.append(
            "⚠ "
            + i.getMedicineName()
            + " ("
            + i.getQuantity()
            + " left)\n");
}

         for (Inventory i :
        inventoryService.getExpiringItems()) {

    notifications.append(
            "⚠ Expiring Soon: "
            + i.getMedicineName()
            + " ("
            + i.getExpiryDate()
            + ")\n");
}
      AppointmentDAO dao =
        new AppointmentDAO();

for (Appointment a :
        dao.getTodayAppointments()) {

    notifications.append(
            "📅 Appointment : Patient "
            + a.getPatientId()
            + " Doctor "
            + a.getDoctorId()
            + "\n");
}

       LabTestDAO labDAO =
        new LabTestDAO();

for (LabTest t :
        labDAO.getPendingTests()) {

    notifications.append(
            "🧪 "
            + t.getTestName()
            + " for Patient "
            + t.getPatientId()
            + "\n");
}
    }
}