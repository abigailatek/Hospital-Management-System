package com.hms.utils;

public class Logger {

    private Logger() {
    }

    public static void info(String message) {

        System.out.println("[INFO] " + message);

    }

    public static void success(String message) {

        System.out.println("[SUCCESS] " + message);

    }

    public static void error(String message) {

        System.err.println("[ERROR] " + message);

    }

}