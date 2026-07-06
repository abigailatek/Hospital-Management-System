package com.hms.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\d{10,15}$");

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null &&
               EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null &&
               PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidDateOfBirth(LocalDate dob) {
        return dob != null &&
               !dob.isAfter(LocalDate.now());
    }
}