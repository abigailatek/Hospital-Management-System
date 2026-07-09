package com.hms.models;

import java.time.LocalDateTime;

public class User {

    private int userID;
    private String username;
    private String passwordHash;
    private int roleID;
    private String roleName;
    private LocalDateTime createdAt;
    public String email;
    public String name;
    public String phone;
    public String address;
    public String password;

    // Default Constructor
    public User() {
    }

    // Constructor without createdAt
    public User(int userID, String username, String passwordHash, int roleID, String roleName) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleID = roleID;
        this.roleName = roleName;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}