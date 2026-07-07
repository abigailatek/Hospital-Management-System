package com.hms.ui;

import com.hms.Dashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private final Color PRIMARY_GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(232, 245, 233);

    public LoginScreen() {

        setTitle("Hospital Management System");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setBackground(PRIMARY_GREEN);
        header.setPreferredSize(new Dimension(500,100));

        JLabel title = new JLabel("Hospital Management System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        header.add(title);

        // ================= LOGIN PANEL =================

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(new EmptyBorder(30,40,30,40));
        loginPanel.setLayout(new GridLayout(8,1,10,10));

        JLabel welcome = new JLabel("Welcome Back");
        welcome.setFont(new Font("Segoe UI", Font.BOLD,20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginBtn = new JButton("LOGIN");
        JButton clearBtn = new JButton("CLEAR");

        loginBtn.setBackground(PRIMARY_GREEN);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        clearBtn.setBackground(LIGHT_GREEN);

        loginPanel.add(welcome);

        loginPanel.add(new JLabel("Username"));
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password"));
        loginPanel.add(passwordField);

        loginPanel.add(loginBtn);
        loginPanel.add(clearBtn);

        JLabel footer = new JLabel("©️ 2026 Hospital Management System", SwingConstants.CENTER);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        // ================= BUTTONS =================

        loginBtn.addActionListener(e -> login());

        clearBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
    }

    private void login(){

        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if(username.equals("admin") && password.equals("admin123")){

            dispose();

            new Dashboard().setVisible(true);

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}