package com.twu;

public class User {
    private String username;
    private String password;
    private int type;

    public User(String username, String password, int type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
