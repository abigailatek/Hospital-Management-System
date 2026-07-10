package com.hms.ui.components;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticCard extends JPanel {

    private JLabel valueLabel;

    public StatisticCard(String icon, String title, int value) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225,225,225)),
                new EmptyBorder(20,20,20,20)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Theme.PRIMARY_GREEN);

        top.add(iconLabel, BorderLayout.WEST);
        top.add(titleLabel, BorderLayout.CENTER);

        valueLabel = new JLabel(String.valueOf(value));
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        valueLabel.setForeground(new Color(40,40,40));

        JLabel subtitle = new JLabel("Total Records");
        subtitle.setForeground(Color.GRAY);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        bottom.add(valueLabel);
        bottom.add(subtitle);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);
    }

    public void setValue(int value) {
        valueLabel.setText(String.valueOf(value));
    }
}