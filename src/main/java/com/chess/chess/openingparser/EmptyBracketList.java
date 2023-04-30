package com.chess.chess.openingparser;

import java.util.ArrayList;
import java.util.Collection;

public class EmptyBracketList extends ArrayList<Pair> {

    @Override
    public boolean add(Pair pair) {
        if ((pair.getTo() - pair.getFrom()) > 3) {
            return super.add(pair);
        }
        return false;
    }

    @Override
    public void add(int index, Pair pair) {
        if ((pair.getTo() - pair.getFrom()) > 3) {
            super.add(index, pair);
        }
    }

    @Override
    public boolean addAll(Collection<? extends Pair> c) {
        c.forEach(pair -> add(pair));
        return true;
    }
}
