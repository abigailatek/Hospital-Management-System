package com.hms.ui;
 import com.hms.Dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class LoginScreen extends JFrame{
     final private Font mainFont = new Font("Segoe print", Font.BOLD,18); 
     final private Color themeColor = new Color(25,80,45);
JTextField tfEmail;
JPasswordField pfPassword;
JTextField tfUsername;

public void initialize() {
    /************Form Panel ************/
    JLabel lblHospitalName = new JLabel("LifeCare Hospital", SwingConstants.CENTER);
    JLabel lblUsername = new JLabel("Username");
    lblUsername.setFont(mainFont);
    lblUsername.setForeground(new Color(0,102,102));

    tfUsername = new JTextField();
    tfUsername.setFont(mainFont);
    tfUsername.setBackground(Color.WHITE);
    tfUsername.setBorder(BorderFactory.createLineBorder(new Color(245,245,220),2));

    lblHospitalName.setFont(new Font("Georgia", Font.BOLD, 30));
    lblHospitalName.setForeground(new Color(218, 165, 32));

    JLabel IbEmail = new JLabel("Email");
    IbEmail.setFont(mainFont);
    IbEmail.setForeground(new Color(245,245,220));

    tfEmail = new JTextField();
    tfEmail.setFont(mainFont);
    tfEmail.setBackground(Color.WHITE);
    tfEmail.setBorder(BorderFactory.createLineBorder(new Color(102,204,170),2));

    JLabel IbPassword = new JLabel("Password");
    IbPassword.setFont(mainFont);
    IbPassword.setForeground(new Color(245,245,220));

    pfPassword = new JPasswordField();
    pfPassword.setFont(mainFont);
    pfPassword.setBackground(Color.WHITE);
    pfPassword.setBorder(BorderFactory.createLineBorder(new Color(102,204,170),2));

    JCheckBox cbShowPassword = new JCheckBox("Show Password");
cbShowPassword.setFont(mainFont);
cbShowPassword.setBackground(themeColor);

cbShowPassword.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cbShowPassword.isSelected()) {
            pfPassword.setEchoChar((char) 0);
        } else {
            pfPassword.setEchoChar('\u2022');
        }
    }
});

JButton btnForgotPassword = new JButton("Forgot Password?");
    btnForgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    btnForgotPassword.setForeground(new Color(0, 102, 204));
    btnForgotPassword.setBorderPainted(false);
    btnForgotPassword.setContentAreaFilled(false);
    btnForgotPassword.setFocusPainted(false);
    btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

    btnForgotPassword.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
            LoginScreen.this,
            "Please contact the system administrator to reset your password.",
            "Forgot Password",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
});


    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(0,1,10,10));
    formPanel.setBackground(themeColor);
    formPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
    formPanel.add(lblHospitalName);
    formPanel.add(lblUsername);
    formPanel.add(tfUsername);
    formPanel.add(IbEmail);
    formPanel.add(tfEmail);
    formPanel.add(IbPassword);
    formPanel.add(pfPassword);
    formPanel.add(cbShowPassword);
    formPanel.add(btnForgotPassword);

    

    /************Buttons Panel************/
    JButton btnLogin = new JButton("Login");
    btnLogin.setFont(mainFont);
    btnLogin.setBackground(new Color(102,204,170));
    btnLogin.setForeground(Color.WHITE);
    btnLogin.setFocusPainted(false);

    btnLogin.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login button clicked");
            String username = tfUsername.getText();
            String email = tfEmail.getText();
            String password = String.valueOf(pfPassword.getPassword());

    User user = getAuthenticatedUser(username, email, password);

            if (user != null){
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(LoginScreen.this,
                    "Email or Password Invalid",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }


    });

    JButton btnCancel = new JButton("Cancel");
    btnCancel.setFont(mainFont);
    btnCancel.setBackground(new Color(255,153,153));
    btnCancel.setForeground(Color.WHITE);
    btnCancel.setFocusPainted(false);

    btnCancel.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            dispose();
        }
        
    });

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(1,2,10,0));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
    buttonsPanel.setBackground(themeColor);
    buttonsPanel.add(btnLogin);
    buttonsPanel.add(btnCancel);



    /************Initialise the Frame************/
    add(formPanel,BorderLayout.NORTH);
    add(buttonsPanel,BorderLayout.SOUTH);
    getContentPane().setBackground(themeColor);


    setTitle("Login Form");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(400,500);
    setMinimumSize(new Dimension(350,450));
    //setResizeable(false);
    setLocationRelativeTo(null);
    setVisible(true);
}


private User getAuthenticatedUser(String username, String email, String password) {
    User user = null;

    final String DB_URL = "jdbc:sqlserver://localhost:1600;databaseName=HMS;encrypt=true;trustServerCertificate=true";
    final String USERNAME = "sa";
    final String PASSWORD = "12345678";
    try{
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        //Connected to database successfully........
        String sql = "SELECT * FROM users WHERE username = ? AND email = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,password);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.name = resultSet.getString("name");
            user.email = resultSet.getString("email");
            user.phone = resultSet.getString("phone");
            user.address = resultSet.getString("address");
            user.password = resultSet.getString("password");
        }
        preparedStatement.close();
        conn.close();
    }catch(Exception e){
        System.out.println("Database connection failed!");
    }


    return user;
}

public static void main(String[] args) {
    LoginScreen loginScreen = new LoginScreen();
    loginScreen.initialize();
}
}