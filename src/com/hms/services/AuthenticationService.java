package com.hms.services;

import com.hms.dao.UserDAO;
import com.hms.models.User;

public class AuthenticationService {

    private final UserDAO userDAO;

    public AuthenticationService() {
        userDAO = new UserDAO();
    }

    /**
     * Authenticates a user.
     *
     * @param username The entered username.
     * @param password The entered password.
     * @return User object if authentication succeeds, otherwise null.
     */
    public User login(String username, String password) {

        // Validate input
        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        return userDAO.authenticate(username.trim(), password.trim());
    }

}