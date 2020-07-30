package com.twu.users;

import com.twu.RankingSystem;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, 0);
        menus = new String[]{"查看热搜排行榜", "添加热搜", "添加超级热搜", "删除热搜"};
    }

    @Override
    public void addSuperTopic(RankingSystem core) {
        try {
            core.addSuperTopic(this);
            System.out.println("添加成功");
        } catch (Exception e){
            System.out.println("添加失败");
        }
    }

    @Override
    public void removeTopic(RankingSystem core) {
        try {
            core.removeTopic(this);
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println("删除失败");
        }
    }
}
