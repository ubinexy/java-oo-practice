package com.twu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class RankingSystem {

    private int MAX_RANKINGS = 5;
    private ArrayList<Event> events;
    private int[] rankingsPrice;

    public RankingSystem() {
        events = new ArrayList<>(0);
        rankingsPrice = new int[MAX_RANKINGS];
    }

    public Event selectEvent() {
        System.out.println("你要选择哪个热搜事件：");
        return events.get(0);
    }

    public void addEvent() {
        System.out.println("请输入要添加的热搜名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().trim();

        boolean find = events.stream().anyMatch(x->x.getName().equals(name));
        if(find) {
            System.out.println("已经有这条热搜了");
        } else {
            events.add(new Event(name));
        }
    }

    public void addSuperEvent(User user) {
        if(user.getType() == 1) return;
        System.out.println("请输入要添加的热搜名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().trim();

        boolean find = events.stream().anyMatch(x->x.getName().equals(name));
        if(find) {
            System.out.println("已经有这条热搜了");
        } else {
            events.add(new SuperEvent(name));
        }
    }

    public void seeRankings() {
        System.out.println("热搜排行榜：");

        for(int i = 0; i < MAX_RANKINGS; i++) {
            String s = "";
            if(events != null) {
                if (i < events.size()) s = events.get(i).toString();
            }
            System.out.printf("%d. %s\n", i+1, s);

        }
    }

    public void voteEvent(int number, Event e) {
        e.addVotes(number);
    }

    public void setEventRanking() throws UnrecognizedInputException {
        System.out.println("你要竞价的名次是：");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().trim();

        int ranking;
        try {
            ranking = Integer.parseInt(answer);
        } catch(NumberFormatException e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }
        selectEvent();

        System.out.println("你要出的价格是：(请输入整数)");
        scanner = new Scanner(System.in);
        answer = scanner.next().trim();

        try {
            int money = Integer.parseInt(answer);
            if(money > rankingsPrice[ranking]) {
                rankingsPrice[ranking] = money;
            }
        } catch(NumberFormatException e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }
    }
}
