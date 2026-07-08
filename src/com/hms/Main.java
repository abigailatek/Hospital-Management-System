package com.hms;

import com.hms.ui.User;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public void initialize(User user){

        /**************Info panel ***************/
        JPanel InfoPanel = new JPanel();
        InfoPanel.setLayout(new GridLayout(0,2,5,5));
        InfoPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        InfoPanel.add(new JLabel("Name"));
        InfoPanel.add(new JLabel(user.name));
        InfoPanel.add(new JLabel("Email"));
        InfoPanel.add(new JLabel(user.email));
        InfoPanel.add(new JLabel("Phone"));
        InfoPanel.add(new JLabel(user.phone));
        InfoPanel.add(new JLabel("Address"));
        InfoPanel.add(new JLabel(user.address));

        add(InfoPanel,BorderLayout.NORTH);

        Component[] labels = InfoPanel.getComponents();
        for (int i = 0; i < labels.length;i++){
            labels[i].setFont(new Font("Segeo print", Font.BOLD,18));
        }



        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100,650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
