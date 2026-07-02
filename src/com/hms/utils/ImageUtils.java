package com.hms.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageUtils {

    public static ImageIcon loadIcon(String filename, int width, int height) {

        URL url = ImageUtils.class.getResource("/com/hms/assets/icons/" + filename);

        if (url == null) {
            System.out.println("Image not found: " + filename);
            return null;
        }

        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }
}