package com.twu.users;

import com.twu.RankingSystem;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password, 0);
    }

    @Override
    public void addSuperEvent(RankingSystem core) {
        core.addSuperEvent(this);
    }

    @Override
    public void removeEvent(RankingSystem core) {
        core.removeEvent(this);
    }
}
