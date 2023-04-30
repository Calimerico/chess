package com.chess.chess.openingparser;

public class BracketMatch {
    private BracketMatch parent;
    private int from;
    private int to;

    public BracketMatch(BracketMatch parent, int from, int to) {
        this.parent = parent;
        this.from = from;
        this.to = to;
    }

    public BracketMatch getParent() {
        return parent;
    }

    public void setParent(BracketMatch parent) {
        this.parent = parent;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
