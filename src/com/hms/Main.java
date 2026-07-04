package com.hms;

import com.hms.ui.LoginScreen;
import javax.swing.*;

/**
 * Main.java
 * ---------------------------------------------------------------------
 * Application entry point.
 * Responsibilities:
 *   1. Apply the operating system's native Look and Feel so the app
 *      blends in with the user's desktop (Windows, macOS, Linux) while
 *      our custom UITheme colors/fonts still layer on top cleanly.
 *   2. Launch the single LoginFrame window on the Swing Event Dispatch
 *      Thread (EDT), as required for thread-safe Swing UI construction.
        * Demo credentials (see AuthService#seedDemoAccount):
        *      Username: admin      Password: Admin123      Role: Administrator
 * ---------------------------------------------------------------------
 */
public class Main {

    static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}