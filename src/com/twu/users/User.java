package com.twu.users;

import com.twu.RankingSystem;

public class User {
    private String username;
    private String password;
    private int type;
    public String[] menus;

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
        core.showRankings();
    }

    public void addTopic(RankingSystem core) {
        try {
            core.addTopic();
            System.out.println("添加成功");
        } catch(Exception e) {
            System.out.println("添加失败");
        }

    }

    public void buyTopicRanking(RankingSystem core) {
        // this function should never be invoked
    }

    public void voteTopic(RankingSystem core) {
        // this function should never be invoked
    }

    public void addSuperTopic(RankingSystem core) {
        // this function should never be invoked
    }

    public void removeTopic(RankingSystem core) {
        // this function should never be invoked
    }
}
