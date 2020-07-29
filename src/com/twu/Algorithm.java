package com.twu;

import com.twu.topics.Topic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algorithm {
    private static int MAX_RANKINGS;
    private final int[] currentPrices;

    public Algorithm(int MAX_RANKINGS) {
        this.currentPrices = new int[MAX_RANKINGS];
        this.MAX_RANKINGS = MAX_RANKINGS;
        IntStream.range(0, MAX_RANKINGS).forEach(
                i->{
                    currentPrices[i] = 0;
                }
        );
    }

    public boolean aduction(int position, int money) {
        // 当出价高于或等于竞拍目标当前的价格，返回值为真。
        // 同时，更新 currentPrices[] 数组。
        // 其中，currentPrices[i] 表示热搜榜上第i条热搜的价格。
        //
        // 竞价规则：
        // 1。总是保证对于任意 m < n，满足 currentPrices[m] < currentPrices[n]
        //    或 currentPrices[m] = currentPrices[n]。
        //
        // 2。当竞拍成功时：
        //    竞拍目标之上的热搜位置，如价格小于出价，则价格也更新为出价。
        //    竞拍目标之下的热搜位置，价格由上一位顺延。
        //
        // 例如：
        // currentPrices[5] = {1, 1, 1, 0, 0}
        // 现在，榜单外的某条热搜，我们用2 个单位竞价榜单上第2 条热搜的位置。
        // 那么，currentPrices[5] 会更新为 {2, 2, 1, 1, 0}
        //

        if(position > MAX_RANKINGS) return false;
        if(position < 0) return false;

        if(money < currentPrices[position]) return false;

        for(int i = MAX_RANKINGS-1; i > position; i--) {
            currentPrices[i] = currentPrices[i-1];
        }
        for(int i = position; i >= 0; i--) {
            currentPrices[i] = money > currentPrices[i] ? money : currentPrices[i];
        }
        return true;
    }

    public void ranking(int position, Topic topic, List<Topic> topicList) {
        // 插入排序，当 aduction() 返回为真之后调用。
        topicList.remove(topic);
        topicList.add(position, topic);
    }

    public ArrayList<Topic> sorted(Topic topic, ArrayList<Topic> topics) {
        // 更新榜单的排序，当用户增加某个热搜的票数之后调用。
        // 注意：排序只在与这个热搜，有相同的竞价价格的集合中进行。

        int money = currentPrices[topics.indexOf(topic)];

        int[] indices= IntStream.range(0, topics.size())
                .filter(i -> currentPrices[i] == money).toArray();

        ArrayList<Topic> sorted = new ArrayList<>();
        for (int i=0 ; i < indices.length; i++) {
            sorted.add(topics.get(indices[i]));
        }

        List<Topic> sorted2 = sorted.stream()
                .sorted(Comparator.comparing(Topic::getVotes).reversed())
                .collect(Collectors.toList());

        for (int i=0 ; i < indices.length; i++) {
            topics.set(indices[i], sorted2.get(i));
        }
        return topics;
    }

    public void delete(Topic topic, ArrayList<Topic> topics) {
        // 删除某条热搜事件，
        // 同时更新 currentPrices[] 数组。
        //
        int pos = topics.indexOf(topic);

        for(int i = pos; i < MAX_RANKINGS-1; i++) {
            currentPrices[i] = currentPrices[i+1];
        }
        if(pos < MAX_RANKINGS-1)
            currentPrices[MAX_RANKINGS-1] = 0;

        topics.remove(topic);
    }
}
