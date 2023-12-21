package com.example.fithub;

public class UserSession {
    private static UserSession instance;

    private boolean isAuthenticated;
    private String username;

    private UserSession() {
        isAuthenticated = false;
        username = null;
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearSession() {
        isAuthenticated = false;
        username = null;
    }
}
