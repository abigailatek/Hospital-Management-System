package com.hms.ui.components;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticCard extends JPanel {

    private JLabel valueLabel;

    public StatisticCard(
            String icon,
            String title,
            int value) {

        setLayout(new BorderLayout(15, 15));

        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 225, 225), 1),
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20)));

        setPreferredSize(
                new Dimension(
                        260,
                        180));

        //---------------------------------
        // TOP SECTION
        //---------------------------------

        JPanel header =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0));

        header.setOpaque(false);

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        32));

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        titleLabel.setForeground(
                Theme.PRIMARY_GREEN);

        header.add(iconLabel);
        header.add(titleLabel);

        //---------------------------------
        // VALUE SECTION
        //---------------------------------

        JPanel center =
                new JPanel();

        center.setOpaque(false);
        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS));

        valueLabel =
                new JLabel(
                        String.valueOf(value));

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        52));

        valueLabel.setForeground(
                Theme.PRIMARY_GREEN);

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel subtitle =
                new JLabel(
                        "Total Records");

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        subtitle.setForeground(
                Color.GRAY);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(valueLabel);
        center.add(Box.createVerticalStrut(5));
        center.add(subtitle);
        center.add(Box.createVerticalGlue());

        //---------------------------------
        // ADD COMPONENTS
        //---------------------------------

        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    //---------------------------------
    // UPDATE VALUE
    //---------------------------------

    public void setValue(int value) {
        valueLabel.setText(
                String.valueOf(value));
    }
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 =
            (Graphics2D) g;

    g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(Color.WHITE);

    g2.fillRoundRect(
            0,
            0,
            getWidth()-1,
            getHeight()-1,
            30,
            30);

    g2.setColor(
            new Color(
                    225,
                    225,
                    225));

    g2.drawRoundRect(
            0,
            0,
            getWidth()-1,
            getHeight()-1,
            30,
            30);

    g2.setColor(
            Theme.PRIMARY_GREEN);

    g2.fillRoundRect(
            0,
            0,
            getWidth(),
            10,
            30,
            30);
}
    
}