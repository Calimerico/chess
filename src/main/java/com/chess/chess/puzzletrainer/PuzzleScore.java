package com.chess.chess.puzzletrainer;

public class PuzzleScore {
    private final int rating;
    private final int seconds;
    private final boolean fail;

    public PuzzleScore(int rating, int seconds, boolean fail) {
        this.rating = rating;
        this.seconds = seconds;
        this.fail = fail;
    }

    public int getRating() {
        return rating;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isFail() {
        return fail;
    }
}
