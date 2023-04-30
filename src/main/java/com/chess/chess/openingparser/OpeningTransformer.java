package com.chess.chess.openingparser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OpeningTransformer {
    private final BracketsFinder bracketsFinder = new BracketsFinder();
    public List<String> transform(String parent, String pgn) {
        List<String> result = new ArrayList<>();
        List<Pair> rootBrackets = bracketsFinder.findRootBrackets(pgn);
        if (rootBrackets.isEmpty()) {
            String s = parent + pgn;
            return Arrays.asList(s.replaceAll("\\d{1,2}\\.{3}","").replaceAll(" +", " "));
        }

        List<Pair> emptySlots = new EmptyBracketList();
        emptySlots.add(new Pair(0,rootBrackets.get(0).getFrom() - 1));
        for(int i = 0; i < rootBrackets.size(); i++) {
            if (i == rootBrackets.size() - 1) {
                emptySlots.add(new Pair(rootBrackets.get(i).getTo(),pgn.length()));
            } else {
                emptySlots.add(new Pair(rootBrackets.get(i).getTo(), rootBrackets.get(i + 1).getFrom()));

            }
        }
        emptySlots.add(new Pair(rootBrackets.get(rootBrackets.size() - 1).getTo(),pgn.length()));

        for(int i = 0; i < rootBrackets.size(); i++) {
            List<Pair> emptyBeforeBrackets = new ArrayList<>();
            List<Pair> emptyAfterBrackets = new ArrayList<>();
            for (Pair pair : emptySlots) {
                if (pair.getFrom() < rootBrackets.get(i).getFrom()) {
                    emptyBeforeBrackets.add(pair);
                } else {
                    emptyAfterBrackets.add(pair);
                }
            }
            String tempBeforeBrackets = "";
            for (Pair emptyBeforeBracket : emptyBeforeBrackets) {
                tempBeforeBrackets+= trimBrackets(pgn.substring(emptyBeforeBracket.getFrom(), emptyBeforeBracket.getTo()));
            }
            String arg = parent + tempBeforeBrackets;
            String arg1 = "";
            if (!arg.isBlank()) {
                String[] split = arg.split(" ");
                for(int j = 0; j < split.length - 2; j++) {
                    arg1= arg1 + split[j] + " ";
                }
            }
            result.addAll(transform(arg1, pgn.substring(rootBrackets.get(i).getFrom(),rootBrackets.get(i).getTo())));
            String tempAfterBrackets = "";
            for (Pair emptyAfterBracket : emptyAfterBrackets) {
                tempAfterBrackets+= trimBrackets(pgn.substring(emptyAfterBracket.getFrom(), emptyAfterBracket.getTo()));

            }
            String res = parent + tempBeforeBrackets + tempAfterBrackets;
            result.add(res.replaceAll("\\d{1,2}\\.{3}","").replaceAll(" +", " "));
        }
        return new ArrayList<>(new HashSet<>(result));
    }

    public static String trimBrackets(String text) {
        String trimBy = ")";
        String trimBy2 = "(";
        int beginIndex = 0;
        int endIndex = text.length();

        while (text.substring(beginIndex, endIndex).startsWith(trimBy) || text.substring(beginIndex, endIndex).startsWith(trimBy2)) {
            beginIndex += trimBy.length();
        }

        while (text.substring(beginIndex, endIndex).endsWith(trimBy) || text.substring(beginIndex, endIndex).endsWith(trimBy2)) {
            endIndex -= trimBy.length();
        }

        return text.substring(beginIndex, endIndex);
    }
}
