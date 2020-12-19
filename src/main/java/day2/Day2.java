package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day2 {

    private static Logger logger = Logger.getLogger(Day2.class.getName());

    public static void main(String[] args) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("inputs/day2/input.txt"))) {
            String line;
            List<Integer> firsts = new ArrayList<>();
            List<Integer> seconds = new ArrayList<>();
            List<Character> letters = new ArrayList<>();
            List<String> passwords = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                int hyphenIndex = line.indexOf('-');
                int firstSpaceIndex = line.indexOf(' ');
                int colonIndex = line.indexOf(':');
                firsts.add(Integer.parseInt(line.substring(0, hyphenIndex)));
                seconds.add(Integer.parseInt(line.substring(hyphenIndex+1, firstSpaceIndex)));
                letters.add(line.charAt(firstSpaceIndex+1));
                passwords.add(line.substring(colonIndex+2));
            }

            int problem1Answer = problem1(firsts, seconds, letters, passwords);
            int problem2Answer = problem2(firsts, seconds, letters, passwords);
            log(Level.INFO, Integer.toString(problem1Answer));
            log(Level.INFO, Integer.toString(problem2Answer));
        }
        catch (IOException e) {
            log(Level.SEVERE, e.getMessage());
        }
    }

    /*
    How many passwords are valid according to their policies
     */
    private static int problem1(List<Integer> firsts, List<Integer> seconds, List<Character> letters, List<String> passwords) {
        int count = 0;
        for(int i = 0; i < passwords.size(); i++) {
            if(isValidPassword1(firsts.get(i), seconds.get(i), letters.get(i), passwords.get(i))) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidPassword1(int first, int second, char letter, String password) {
        long numChars = password.chars().filter(ch -> ch == letter).count();
        return numChars >= first && numChars <= second;
    }

    /*
    Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on.
    (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
    Exactly one of these positions must contain the given letter.
    Other occurrences of the letter are irrelevant for the purposes of policy enforcement
     */
    private static int problem2(List<Integer> firsts, List<Integer> seconds, List<Character> letters, List<String> passwords) {
        int count = 0;
        for(int i = 0; i < passwords.size(); i++) {
            if(isValidPassword2(firsts.get(i), seconds.get(i), letters.get(i), passwords.get(i))) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidPassword2(int first, int second, char letter, String password) {
        return (first <= password.length() && password.charAt(first-1) == letter)
                ^ (second <= password.length() && password.charAt(second-1) == letter);
    }

    private static void log(Level level, String string) {
        if(logger.isLoggable(level)) {
            logger.log(level, string);
        }
    }
}
