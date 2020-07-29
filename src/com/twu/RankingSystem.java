package com.twu;

import com.twu.topics.Topic;
import com.twu.topics.SuperTopic;
import com.twu.exceptions.UnrecognizedInputException;
import com.twu.users.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class RankingSystem {

    private int MAX_RANKINGS = 5;
    private ArrayList<Topic> topics;
    private Algorithm algorithm;

    public RankingSystem() {
        topics = new ArrayList<>(0);
        algorithm = new Algorithm(MAX_RANKINGS);
    }

    public void showRankings() {
        System.out.println("热搜排行榜：");

        for(int i = 0; i < MAX_RANKINGS; i++) {
            String s = "";
            if (i < topics.size()) s = topics.get(i).toString();
            System.out.printf("%d. %s\n", i+1, s);
        }
    }

    public Topic selectTopic() throws UnrecognizedInputException {
        System.out.printf("你要选择哪个热搜事件：(1-%d)\n", topics.size());
        IntStream.range(0, topics.size()).forEach(i-> {
            System.out.printf("%d. %s\n", i + 1, topics.get(i).toString());
        });
        Scanner scanner = new Scanner(System.in);

        try {
            int i = scanner.nextInt(); i--;
            if(i >= 0 && i < topics.size())
                return topics.get(i);
        } catch(Exception e) {

        }
        throw new UnrecognizedInputException("无法识别的输入");
    }

    public void addTopic() {
        System.out.println("请输入要添加的热搜名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().trim();

        boolean find = topics.stream().anyMatch(x->x.getName().equals(name));
        if(find) {
            System.out.println("已经有这条热搜了");
            System.out.println("添加失败");
        } else {
            topics.add(new Topic(name));
            System.out.println("添加成功");
        }
    }

    public void addSuperTopic(User user) {
        if(user.getType() == 1) return;
        System.out.println("请输入要添加的热搜名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().trim();

        boolean find = topics.stream().anyMatch(x->x.getName().equals(name));
        if(find) {
            System.out.println("已经有这条热搜了");
            System.out.println("添加失败");
        } else {
            topics.add(new SuperTopic(name));
            System.out.println("添加成功");
        }
    }

    public void removeTopic(User admin) {
        if (admin.getType() == 1) return;
        try {
            Topic e = selectTopic();
            algorithm.delete(e, topics);
            System.out.println("删除成功");
        } catch (UnrecognizedInputException e) {
            System.out.println("删除失败");
        }
    }


    public void voteTopic(int number, Topic e) {
        e.addVotes(number);

        topics = algorithm.sorted(e, topics);
    }

    public void setEventRanking() throws Exception {
        Topic topic = selectTopic();
        int pos = this.topics.indexOf(topic);
        int maxAvailable = Math.min(pos+1, MAX_RANKINGS);

        System.out.printf("你要竞价的名次是：(1-%d)\n", maxAvailable);
        Scanner scanner = new Scanner(System.in);

        int ranking;
        try {
            ranking = scanner.nextInt(); ranking--;
            if(ranking < 0 || ranking >= maxAvailable) {
                throw new UnrecognizedInputException("超出范围");
            }
        } catch(Exception e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }

        System.out.println("你要出的价格是：(请输入整数)");
        scanner = new Scanner(System.in);


        try {
            int money = scanner.nextInt();

            if(algorithm.aduction(ranking, money)) {
                algorithm.ranking(ranking, topic, this.topics);
            } else {
                throw new Exception("竞价金额不够");
            }
        } catch(Exception e) {
            throw new UnrecognizedInputException("无法识别的输入");
        }
    }


}
