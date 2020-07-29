package com.twu.users;

import com.twu.RankingSystem;

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

    public void seeRankings(RankingSystem core){
        core.seeRankings();
    }

    public void addEvent(RankingSystem core) {
        core.addEvent();
    }

    public void buyEventRanking(RankingSystem core) {
        // this function should never be invoked
    }

    public void voteEvent(RankingSystem core) {
        // this function should never be invoked
    }

    public void addSuperEvent(RankingSystem core) {
        // this function should never be invoked
    }

    public void removeEvent(RankingSystem core) {
        // this function should never be invoked
    }
}
