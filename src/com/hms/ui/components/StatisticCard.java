package com.hms.ui.components;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticCard extends JPanel {

    private JLabel valueLabel;

    public StatisticCard(String icon,
                         String title,
                         int value) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225,225,225)),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25)));

        setPreferredSize(
                new Dimension(
                        250,
                        105));

        //---------------------------------
        // Top section
        //---------------------------------

        JPanel top =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0));

        top.setOpaque(false);

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        30));

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        titleLabel.setForeground(
                Theme.PRIMARY_GREEN);

        top.add(iconLabel);
        top.add(titleLabel);

        //---------------------------------
        // Bottom section
        //---------------------------------

        JPanel bottom =
                new JPanel();

        bottom.setOpaque(false);

        bottom.setLayout(
                new BoxLayout(
                        bottom,
                        BoxLayout.Y_AXIS));

        valueLabel =
                new JLabel(
                        String.valueOf(value));

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36));

        valueLabel.setForeground(
                new Color(
                        40,
                        40,
                        40));

        JLabel subtitle =
                new JLabel(
                        "Total Records");

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        subtitle.setForeground(
                Color.GRAY);

        bottom.add(valueLabel);
        bottom.add(Box.createVerticalStrut(5));
        bottom.add(subtitle);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);
    }

    public void setValue(int value) {
        valueLabel.setText(
                String.valueOf(value));
    }
}