package com.twu;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password, 0);
    }

    @Override
    public void addSuperEvent(RankingSystem core){
        core.addSuperEvent(this);
    }

}
