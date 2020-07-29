package com.twu;

import com.twu.events.Event;
import com.twu.events.SuperEvent;
import com.twu.exceptions.UnrecognizedInputException;
import com.twu.users.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class RankingSystem {

    private int MAX_RANKINGS = 5;
    private ArrayList<Event> events;
    private Algorithm algorithm;

    public RankingSystem() {
        events = new ArrayList<>(0);
        algorithm = new Algorithm(MAX_RANKINGS);
    }

    public Event selectEvent() throws UnrecognizedInputException {
        System.out.printf("你要选择哪个热搜事件：(1-%d)\n",events.size());
        IntStream.range(0, events.size()).forEach(i-> {
            System.out.printf("%d. %s\n", i + 1, events.get(i).toString());
        });
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().trim();
        try {
            int i = Integer.parseInt(answer); i--;
            if(i >= 0 && i < events.size())
                return events.get(i);
        } catch(NumberFormatException e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }
        throw new UnrecognizedInputException("无法识别的输入");
    }

    public void addEvent() {
        System.out.println("请输入要添加的热搜名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().trim();

        boolean find = events.stream().anyMatch(x->x.getName().equals(name));
        if(find) {
            System.out.println("已经有这条热搜了");
            System.out.println("添加失败");
        } else {
            events.add(new Event(name));
            System.out.println("添加成功");
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
            System.out.println("添加失败");
        } else {
            events.add(new SuperEvent(name));
            System.out.println("添加成功");
        }
    }

    public void seeRankings() {
        System.out.println("热搜排行榜：");

        for(int i = 0; i < MAX_RANKINGS; i++) {
            String s = "";
            if (i < events.size()) s = events.get(i).toString();
            System.out.printf("%d. %s\n", i+1, s);
        }
    }

    public void voteEvent(int number, Event e) {
        e.addVotes(number);

        events = algorithm.sorted(e, events);
    }

    public void setEventRanking() throws Exception {
        Event event = selectEvent();
        int pos = events.indexOf(event);
        int maxAvailable = Math.min(pos+1, MAX_RANKINGS);

        System.out.printf("你要竞价的名次是：(1-%d)\n", maxAvailable);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().trim();

        int ranking;
        try {
            ranking = Integer.parseInt(answer); ranking--;
            if(ranking < 0 || ranking >= maxAvailable) {
                throw new UnrecognizedInputException("超出范围");
            }
        } catch(NumberFormatException e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }

        System.out.println("你要出的价格是：(请输入整数)");
        scanner = new Scanner(System.in);
        answer = scanner.next().trim();

        try {
            int money = Integer.parseInt(answer);

            if(algorithm.aduction(ranking, money)) {
                algorithm.ranking(ranking, event, events);
            } else {
                throw new Exception("竞价金额不够");
            }
        } catch(NumberFormatException e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }
    }

    public void removeEvent(User admin) {
        if (admin.getType() == 1) return;
        try {
            Event e = selectEvent();
            algorithm.delete(e, events);
            System.out.println("删除成功");
        } catch (UnrecognizedInputException e) {
            System.out.println("删除失败");
        }
    }
}
