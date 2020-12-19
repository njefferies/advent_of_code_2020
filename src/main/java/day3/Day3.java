package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day3 {

    private static Logger logger = Logger.getLogger(Day3.class.getName());

    public static void main(String [] args) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("inputs/day3/input.txt"))) {
            List<String> lines = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int problem1Answer = problem1(lines, 3, 1);
            long problem2Answer = problem2(lines);

            log(Level.INFO, Integer.toString(problem1Answer));
            log(Level.INFO, Long.toString(problem2Answer));
        }
        catch (IOException e) {
            log(Level.SEVERE, e.getMessage());
        }
    }

    private static int problem1(List<String> lines, int right, int down) {
        int numTrees = 0;
        int col = right;
        for(int row = down; row < lines.size(); row+=down) {
            String line = lines.get(row);
            if(line.charAt(col) == '#') {
                numTrees++;
            }
            col = (col + right) % line.length();
        }
        return numTrees;
    }

    private static long problem2(List<String> lines) {
        return (long) problem1(lines, 1, 1)
                * problem1(lines, 3, 1)
                * problem1(lines, 5, 1)
                * problem1(lines, 7, 1)
                * problem1(lines, 1, 2);
    }

    private static void log(Level level, String string) {
        if(logger.isLoggable(level)) {
            logger.log(level, string);
        }
    }
}
