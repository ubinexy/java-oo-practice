package com.twu;

public class Event {
    protected String name;
    protected int votes;

    Event(String name) {
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
