package com.hms.ui.components;

import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class CrudToolbar extends JPanel {

    public final JButton btnAdd = new JButton("Add");
    public final JButton btnEdit = new JButton("Edit");
    public final JButton btnDelete = new JButton("Delete");
    public final JButton btnRefresh = new JButton("Refresh");

    public final JTextField txtSearch = new JTextField(18);
    public final JButton btnSearch = new JButton("Search");

    public CrudToolbar() {

        setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        setBackground(Theme.BACKGROUND);

        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnRefresh);

        add(Box.createHorizontalStrut(25));

       add(new JLabel("Search"));
        add(txtSearch);
        add(btnSearch);

    }

}