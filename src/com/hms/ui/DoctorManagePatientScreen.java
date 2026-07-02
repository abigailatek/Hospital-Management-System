package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorManagePatientScreen extends JPanel {

    public DoctorManagePatientScreen() {

        setLayout(new BorderLayout(15,15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("DOCTOR - MANAGE PATIENTS");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        //---------------- Search ----------------

        JPanel top = new JPanel(new BorderLayout(10,10));
        top.setBackground(Theme.BACKGROUND);

        JTextField search = new JTextField();
        JButton searchBtn = new JButton("Search");

        searchBtn.setBackground(Theme.PRIMARY_GREEN);
        searchBtn.setForeground(Color.WHITE);

        top.add(search,BorderLayout.CENTER);
        top.add(searchBtn,BorderLayout.EAST);

        //---------------- Table ----------------

        String[] columns={
                "Patient ID",
                "Name",
                "Gender",
                "Age",
                "Status"
        };

        DefaultTableModel model=new DefaultTableModel(columns,0);

        JTable table=new JTable(model);
        table.setRowHeight(28);

        model.addRow(new Object[]{
                "P001",
                "John Doe",
                "Male",
                32,
                "Admitted"
        });

        model.addRow(new Object[]{
                "P002",
                "Sarah James",
                "Female",
                24,
                "Outpatient"
        });

        JScrollPane tablePane=new JScrollPane(table);

        //---------------- Details ----------------

        JPanel details=new JPanel(new GridLayout(6,2,10,10));
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createTitledBorder("Patient Details"));

        details.add(new JLabel("Patient ID"));
        details.add(new JTextField());

        details.add(new JLabel("Patient Name"));
        details.add(new JTextField());

        details.add(new JLabel("Age"));
        details.add(new JTextField());

        details.add(new JLabel("Gender"));
        details.add(new JTextField());

        details.add(new JLabel("Diagnosis"));
        details.add(new JTextField());

        details.add(new JLabel("Treatment"));
        details.add(new JTextField());

        //---------------- Buttons ----------------

        JPanel buttons=new JPanel();

        JButton update=new JButton("Update");

        JButton prescribe=new JButton("Prescription");

        JButton discharge=new JButton("Discharge");

        update.setBackground(Theme.PRIMARY_GREEN);
        prescribe.setBackground(Theme.PRIMARY_GREEN);
        discharge.setBackground(Color.RED);

        update.setForeground(Color.WHITE);
        prescribe.setForeground(Color.WHITE);
        discharge.setForeground(Color.WHITE);

        buttons.add(update);
        buttons.add(prescribe);
        buttons.add(discharge);

        //---------------- Center ----------------

        JPanel center=new JPanel(new BorderLayout(15,15));
        center.setBackground(Theme.BACKGROUND);

        center.add(top,BorderLayout.NORTH);
        center.add(tablePane,BorderLayout.CENTER);
        center.add(details,BorderLayout.SOUTH);

        add(center,BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);

    }

}
