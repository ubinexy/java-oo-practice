package com.twu.users;

import com.twu.topics.Topic;
import com.twu.RankingSystem;

import java.util.Scanner;

public class OrdinaryUser extends User {

    private int votes = 10;

    public OrdinaryUser(String username, String password) {
        super(username, password, 1);
        menus = new String[]{"查看热搜排行榜", "添加热搜", "给热搜事件投票", "购买热搜"};
    }

    @Override
    public void voteEvent(RankingSystem core) {

        try {
            Topic topic = core.selectTopic();
            queryVote(core, topic);
            System.out.println("投票成功");
        } catch (Exception e) {
            System.out.println("投票失败");
        }
    }

    @Override
    public void buyEventRanking(RankingSystem core) {
        try {
            core.setEventRanking();
            System.out.println("竞价成功");
        } catch (Exception e) {
            System.out.println("竞价失败");
        }
    }

    private void queryVote(RankingSystem core, Topic topic) throws Exception {
        System.out.printf("你现在有%d 票，要给#%s 投几票？\n", votes, topic.getName());
        Scanner scanner = new Scanner(System.in);

        int v = Integer.parseInt(scanner.next());
        if (v <= 0 || v > votes) throw new Exception("无法识别的票数");
        votes -= v;
        core.voteTopic(v, topic);
    }
}
