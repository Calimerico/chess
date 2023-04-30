package com.chess.chess.openingparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BracketsFinder {

    public List<Pair> findRootBrackets(String pgn) {
        List<Pair> pairs = new ArrayList<>();
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i < pgn.length(); i++) {
            if (pgn.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    stack.add(i + 1);
                } else {
                    count++;
                }
            }
            if (pgn.charAt(i) == ')') {
                if (count > 0) {
                    count--;
                } else {
                    pairs.add(new Pair(stack.pop(),i));
                }

            }
        }
        return pairs;
    }
}
