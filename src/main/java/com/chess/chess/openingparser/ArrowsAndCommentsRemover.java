package com.chess.chess.openingparser;

import java.io.*;

public class ArrowsAndCommentsRemover {

    public String process(String opening) {
        String pgn;

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/spasojepetronijevic/Downloads/" + opening + ".pgn"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            pgn = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int start;
        int end;
        while ((start = pgn.indexOf('{')) != -1) {
            end = pgn.indexOf('}');
            pgn = pgn.substring(0,start) + pgn.substring(end + 1);
            System.out.println(pgn.length());
        }
        Writer writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/spasojepetronijevic/Downloads/" + opening + "-parsed.pgn"), "utf-8"));
            writer.write(pgn);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return pgn;
    }
}
