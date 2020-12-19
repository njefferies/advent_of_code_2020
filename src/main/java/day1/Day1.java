package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day1 {

    private static Logger logger = Logger.getLogger(Day1.class.getName());

    public static void main(String [] args) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("inputs/day1/input.txt"))) {
            String line;
            List<Integer> nums = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                nums.add(Integer.valueOf(line));
            }
            int problem1Answer = problem1(nums);
            int problem2Answer = problem2(nums);
            log(Level.INFO, Integer.toString(problem1Answer));
            log(Level.INFO, Integer.toString(problem2Answer));
        }
        catch (IOException e) {
            log(Level.SEVERE, e.getMessage());
        }
    }

    /*
    Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together
     */
    private static int problem1(List<Integer> nums) {
        Set<Integer> set = new HashSet<>(nums);
        for(int num : set) {
            if(set.contains(2020 - num)) {
                return num * (2020 - num);
            }
        }
        return 0;
    }

    /*
    find three numbers in your expense report that meet the same criteria
     */
    private static int problem2(List<Integer> nums) {
        Set<Integer> set = new HashSet<>(nums);
        for(int i = 0; i < nums.size(); i++) {
            int num1 = nums.get(i);
            for(int j = i; j < nums.size(); j++) {
                int num2 = nums.get(j);
                if(set.contains(2020 - num1 - num2)) {
                    return num1 * num2 * (2020 - num1 - num2);
                }
            }
        }
        return 0;
    }

    private static void log(Level level, String string) {
        if(logger.isLoggable(level)) {
            logger.log(level, string);
        }
    }
}
