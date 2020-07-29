package com.twu.topics;

public class SuperTopic extends Topic {
    private int VALUE = 2;

    public SuperTopic(String name) {
        super(name);
    }

    @Override
    public void addVotes(int number) {
        votes += VALUE;
    }

    @Override
    public String toString() {
        return name + "（Hot） " + votes;
    }
}
