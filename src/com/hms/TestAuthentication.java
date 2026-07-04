package com.hms;

import com.hms.models.User;
import com.hms.services.AuthenticationService;

public class TestAuthentication {

    public static void main(String[] args) {

        AuthenticationService auth = new AuthenticationService();

        User user = auth.login("admin", "1234");

        if (user != null) {
            System.out.println("=================================");
            System.out.println("LOGIN SUCCESSFUL");
            System.out.println("Welcome: " + user.getUsername());
            System.out.println("Role: " + user.getRoleName());
            System.out.println("=================================");
        } else {
            System.out.println("LOGIN FAILED");
        }
    }
}