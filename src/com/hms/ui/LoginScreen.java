package com.hms.ui;
 import com.hms.Dashboard;
 import com.hms.DoctorDashboard;
import com.hms.models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class LoginScreen extends JFrame{
     final private Font mainFont = new Font("Segoe UI", Font.PLAIN,16); 
     final private Color themeColor = new Color(25,80,45);
JPasswordField pfPassword;
JTextField tfUsername;
JComboBox<String> cbRole;

public void initialize() {
    /************Form Panel ************/
    JLabel lblHospitalName = new JLabel("LifeCare Hospital", SwingConstants.CENTER);
    JLabel lblUsername = new JLabel("Username");
    lblUsername.setFont(mainFont);
    lblUsername.setForeground(Color.WHITE);

    tfUsername = new JTextField();
    tfUsername.setFont(mainFont);
    tfUsername.setBackground(Color.WHITE);
    tfUsername.setBorder(BorderFactory.createLineBorder(new Color(245,245,220),2));

    JLabel lblRole = new JLabel("Role");
    lblRole.setFont(mainFont);
    lblRole.setForeground(Color.WHITE);

    cbRole = new JComboBox<>(new String[]{"Admin", "Doctor","Patient"});
    cbRole.setFont(mainFont);
    cbRole.setBackground(Color.WHITE);

    lblHospitalName.setFont(new Font("Georgia", Font.BOLD, 30));
    lblHospitalName.setForeground(new Color(218, 165, 32));


    JLabel IbPassword = new JLabel("Password");
    IbPassword.setFont(mainFont);
    IbPassword.setForeground(Color.WHITE);

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
    formPanel.setLayout(new GridLayout(0,1,8,8));
    formPanel.setBackground(themeColor);
    formPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
    formPanel.add(lblHospitalName);
    formPanel.add(lblUsername);
    formPanel.add(tfUsername);
    formPanel.add(lblRole);
    formPanel.add(cbRole);
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
            String password = String.valueOf(pfPassword.getPassword());
String role = cbRole.getSelectedItem().toString();

User user = getAuthenticatedUser(
    username,
    password,
    role
);

           if (user != null) {

    if(role.equals("Admin")){

    Dashboard dashboard = new Dashboard();
    dashboard.setVisible(true);

}
else if(role.equals("Doctor")){

    DoctorDashboard dashboard = new DoctorDashboard();
    dashboard.setVisible(true);

}
else if(role.equals("Patient")){

    PatientScreen screen = new PatientScreen();
    screen.setVisible(true);

}

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

    JButton btnClear = new JButton("Clear");
    btnClear.setFont(mainFont);
    btnClear.setBackground(new Color(255,153,153));
    btnClear.setForeground(Color.WHITE);
    btnClear.setFocusPainted(false);

    btnClear.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tfUsername.setText("");
    pfPassword.setText("");
    cbRole.setSelectedIndex(0);
        }
        
    });

    JButton btnExit = new JButton("Exit");
    btnExit.setFont(mainFont);
    btnExit.setBackground(new Color(255,102,102));
    btnExit.setForeground(Color.WHITE);

    btnExit.addActionListener(e -> System.exit(0));

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(1,3,10,0));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
    buttonsPanel.setBackground(themeColor);
    buttonsPanel.add(btnLogin);
    buttonsPanel.add(btnClear);
    buttonsPanel.add(btnExit);



    /************Initialise the Frame************/
    add(formPanel,BorderLayout.NORTH);
    add(buttonsPanel,BorderLayout.SOUTH);
    getContentPane().setBackground(themeColor);


    setTitle("LifeCare Hospital");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   setSize(450,600);
   setResizable(false);
   setLocationRelativeTo(null);
   setVisible(true);
}

private User getAuthenticatedUser(String username,
                                  String password,
                                  String role) {
    User user = null;

    final String DB_URL = "jdbc:sqlserver://localhost:1600;databaseName=HMS;encrypt=true;trustServerCertificate=true";
    final String USERNAME = "sa";
    final String PASSWORD = "12345678";
    try{
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        //Connected to database successfully........
        String sql = "SELECT * FROM users WHERE username=? AND email=? AND password=? AND role=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(3,password);
        preparedStatement.setString(4, role);
        
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