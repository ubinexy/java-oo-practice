package com.twu;

public class OrdinaryUser extends User {
    private int votes = 10;

    public OrdinaryUser(String username, String password) {
        super(username, password, 1);
    }

    public int getVotes() {
        return votes;
    }
}
