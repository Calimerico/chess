package com.chess.chess.puzzletrainer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.stream.IntStream;

public class Parser {

    public static void main(String[] args) throws IOException {
                List<String> diagramsToShow = Arrays.asList("10-sept-2022", "18-dec-2022");
        List<Path> list = Files.
                walk(Path.of("/Users/spasojepetronijevic/Desktop/pgnparser/puzzle_scores/"))
                .filter(Files::isRegularFile).toList();
        IntStream.range(0, list.size()).forEach(i -> {
            JFrame f = new JFrame("neki tekst");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String p = list.get(i).toAbsolutePath().toString();
            boolean show = false;
            for (String diagramToShow : diagramsToShow) {
                if (p.endsWith(diagramToShow)) {
                    show = true;
                    break;
                }
            }
            if (!show) {
                return;
            }
            f.add(new Graph(p));
            f.setSize(700,700);
            f.setLocation(600 + i*50,200 + i*50);
            f.setVisible(true);
            f.setTitle(p);
        });
    }

    static class Graph extends JPanel {
        private final String path;
        private static final int UNIT = 20;

        public Graph(String path) {
            this.path = path;
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;

            int h = getHeight();
            int w = getWidth();
            // Draw axeX.
            g2.setColor(new Color(0,0,0, 0.2f));
            g2.draw(new Line2D.Double(0, h-250, w, h-250));
            g2.draw(new Line2D.Double(0, h-350, w, h-350));
            g2.draw(new Line2D.Double(0, h-450, w, h-450));
            g2.draw(new Line2D.Double(0, h-550, w, h-550));
            g2.draw(new Line2D.Double(230,h,230,0));
            g2.draw(new Line2D.Double(650,h,650,0));

            g2.setColor(new Color(0,0,0, 1.0f));
            g2.draw(new Line2D.Double(0, h-50, w, h-50)); //to make axisX in the middle
            // Draw axeY.
            g2.draw(new Line2D.Double(50,h,50,0));//to make axisY in the middle of the panel

            try {
                parsePuzzleScores(path).forEach(puzzleScore -> {
//                    g2.fillOval(200,200,5,5);
                    if (puzzleScore.isFail()) {
                        g2.setColor(Color.RED);
                    } else {
                        g2.setColor(Color.GREEN);
                    }
                    g2.fillOval(50 + puzzleScore.getSeconds(), h - 50 - puzzleScore.getRating(),7,7);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public static java.util.List<PuzzleScore> parsePuzzleScores(String path) throws IOException {
        int numSuccess = 0;
        int numFail = 0;
        int secondsSuccess = 0;
        int secondsFail = 0;
        int secondsTotal = 0;
        int ratingSum = 0;
        int ratingSumSuccess = 0;
        int ratingSumFail = 0;
        List<PuzzleScore> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] split = line.split(" ");
                boolean fail = split.length == 5;
                int rating = Integer.parseInt(split[0]);
                int seconds = Integer.parseInt(split[2]);
                scores.add(new PuzzleScore(rating - 2000, seconds, fail));
                ratingSum+=rating;
                secondsTotal += seconds;
                if (fail) {
                    secondsFail+=seconds;
                    ratingSumFail+=rating;
                    numFail++;
                } else {
                    secondsSuccess+=seconds;
                    ratingSumSuccess+=rating;
                    numSuccess++;
                }
                line = br.readLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Path is " + path);
        System.out.println("Accuracy is: " + ((double)(numSuccess)/(double)scores.size()));
        System.out.println("Total count: " + (numFail + numSuccess));
        System.out.println("Avg rating: " + (ratingSum / scores.size()));
        System.out.println("Avg time: " + (secondsTotal / scores.size()));
        System.out.println("Avg success time: " + (secondsSuccess / numSuccess));
        System.out.println("Avg fail time: " + (secondsFail / numFail));
        System.out.println("Avg rating success: " + (ratingSumSuccess / numSuccess));
        System.out.println("Avg rating fail: " + (ratingSumFail / numFail));
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");
        return scores;
    }

    public static void parsePGN(String opening) throws IOException {
        String name = opening;
        String pgn;

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/spasojepetronijevic/Downloads/" + name + ".pgn"))) {
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
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/Users/spasojepetronijevic/Downloads/" + name + "-parsed.pgn"), "utf-8"));
        writer.write(pgn);
        writer.close();
    }
}
