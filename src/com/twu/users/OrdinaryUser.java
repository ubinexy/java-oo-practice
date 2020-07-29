package com.twu.users;

import com.twu.events.Event;
import com.twu.RankingSystem;

import java.util.Scanner;

public class OrdinaryUser extends User {
    private int votes = 10;

    public OrdinaryUser(String username, String password) {
        super(username, password, 1);
    }

    @Override
    public void voteEvent(RankingSystem core) {

        try {
            Event event = core.selectEvent();
            queryVote(core, event);
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

    private void queryVote(RankingSystem core, Event event) throws Exception {
        System.out.printf("你现在有%d 票，要给#%s 投几票？\n", votes, event.getName());
        Scanner scanner = new Scanner(System.in);

        int v = Integer.parseInt(scanner.next());
        if (v <= 0 || v > votes) throw new Exception("无法识别的票数");
        votes -= v;
        core.voteEvent(v, event);
    }
}
