package com.twu.events;

public class SuperEvent extends Event {
    private int VALUE = 2;

    public SuperEvent(String name) {
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
