package com.hms.ui;

import com.hms.models.User;
import com.hms.utils.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LoginScreen extends JFrame {

    private final Font mainFont = new Font("Segoe UI", Font.PLAIN, 16);
    private final Color themeColor = new Color(25, 80, 45);

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JComboBox<String> cbRole;

    public LoginScreen() {
        initialize();
    }

    private void initialize() {

        setTitle("LifeCare Hospital Login");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(themeColor);
        setLayout(new BorderLayout());

        //---------------------------------
        // FORM PANEL
        //---------------------------------

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 8, 8));
        formPanel.setBackground(themeColor);
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50));

        JLabel lblHospital =
                new JLabel(
                        "LifeCare Hospital",
                        SwingConstants.CENTER);

        lblHospital.setFont(
                new Font(
                        "Georgia",
                        Font.BOLD,
                        30));

        lblHospital.setForeground(
                new Color(218, 165, 32));

        JLabel lblUsername =
                new JLabel("Username");

        lblUsername.setFont(mainFont);
        lblUsername.setForeground(Color.WHITE);

        tfUsername = new JTextField();
        tfUsername.setFont(mainFont);

        JLabel lblRole =
                new JLabel("Role");

        lblRole.setFont(mainFont);
        lblRole.setForeground(Color.WHITE);

        cbRole = new JComboBox<>(
                new String[]{
                        "Admin",
                        "Doctor",
                        "Receptionist",
                        "Pharmacist",
                        "Accountant",
                        "Laboratory Technician"
                });

        cbRole.setFont(mainFont);

        JLabel lblPassword =
                new JLabel("Password");

        lblPassword.setFont(mainFont);
        lblPassword.setForeground(Color.WHITE);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JCheckBox cbShow =
                new JCheckBox("Show Password");

        cbShow.setBackground(themeColor);
        cbShow.setForeground(Color.WHITE);

        cbShow.addActionListener(e -> {

            if (cbShow.isSelected()) {
                pfPassword.setEchoChar((char) 0);
            } else {
                pfPassword.setEchoChar('\u2022');
            }

        });

        JButton btnForgot =
                new JButton("Forgot Password?");

        btnForgot.setBorderPainted(false);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setForeground(
                new Color(180, 220, 255));

        btnForgot.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Please contact the system administrator.",
                        "Forgot Password",
                        JOptionPane.INFORMATION_MESSAGE));

        formPanel.add(lblHospital);
        formPanel.add(lblUsername);
        formPanel.add(tfUsername);
        formPanel.add(lblRole);
        formPanel.add(cbRole);
        formPanel.add(lblPassword);
        formPanel.add(pfPassword);
        formPanel.add(cbShow);
        formPanel.add(btnForgot);

        //---------------------------------
        // BUTTON PANEL
        //---------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(1, 3, 10, 0));

        buttonPanel.setBackground(themeColor);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 50, 30, 50));

        JButton btnLogin =
                new JButton("Login");

        JButton btnClear =
                new JButton("Clear");

        JButton btnExit =
                new JButton("Exit");

        btnLogin.setFont(mainFont);
        btnClear.setFont(mainFont);
        btnExit.setFont(mainFont);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        //---------------------------------
        // EVENTS
        //---------------------------------

        btnLogin.addActionListener(
                (ActionEvent e) -> {

                    String username =
                            tfUsername.getText().trim();

                    String password =
                            String.valueOf(
                                    pfPassword.getPassword());

                    String role =
                            cbRole.getSelectedItem()
                                    .toString();

                    User user =
                            authenticateUser(
                                    username,
                                    password,
                                    role);

                    if (user != null) {

                        UserSession.login(user);

                        JOptionPane.showMessageDialog(
                                this,
                                "Welcome "
                                        + user.getUsername());

                        new MainFrame();

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Invalid username or password.",
                                "Login Failed",
                                JOptionPane.ERROR_MESSAGE);

                    }

                });

        btnClear.addActionListener(e -> {

            tfUsername.setText("");
            pfPassword.setText("");
            cbRole.setSelectedIndex(0);

        });

        btnExit.addActionListener(
                e -> System.exit(0));

        //---------------------------------

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    //-------------------------------------
    // AUTHENTICATION
    //-------------------------------------

    private User authenticateUser(
            String username,
            String password,
            String role) {

        User user = null;

        String url =
                "jdbc:sqlserver://localhost:1433;"
                        + "databaseName=HMS;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true";

        String dbUser = "sa";
        String dbPassword = "12345678";

        String sql =
                """
                SELECT u.UserID,
                       u.Username,
                       u.RoleID
                FROM Users u
                JOIN Roles r
                ON u.RoleID = r.RoleID
                WHERE u.Username = ?
                AND u.PasswordHash = ?
                AND r.RoleName = ?
                """;

        try (
                Connection conn =
                        DriverManager.getConnection(
                                url,
                                dbUser,
                                dbPassword);

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserID(
                        rs.getInt("UserID"));

                user.setUsername(
                        rs.getString("Username"));

                user.setRoleID(
                        rs.getInt("RoleID"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    //-------------------------------------

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                LoginScreen::new);

    }
}