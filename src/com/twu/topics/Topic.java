package com.twu.topics;

public class Topic {
    protected String name;
    protected int votes;

    public Topic(String name) {
        this.name = name;
        this.votes = 0;
    }

    public void addVotes(int number) {
        this.votes += number;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return name + " " + votes;
    }
}
