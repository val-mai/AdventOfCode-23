package it.valmai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trebuchet {
    public static void main(String[] args) {

        List<String> stringList = AocInputReader.getLinesFromInput("input.txt");

        Map<String, String> numbersMap = Map.of(
                "one", "1", "two", "2",
                "three", "3", "four", "4",
                "five", "5", "six", "6",
                "seven", "7", "eight", "8", "nine", "9"
        );

        List<Integer> extracted = new ArrayList<>();

        for (String string : stringList) {

            // Replace digit letters with number (part2)
            while (true) {
                boolean replaced = false;
                for (String s : numbersMap.keySet()) {
                    int index = string.indexOf(s);
                    if (index >= 0) {
                        string = string.substring(0, index + 1) + numbersMap.get(s) + string.substring(index + 1 + numbersMap.get(s).length());
                        replaced = true;
                    }
                }
                if (!replaced) break;
            }

            // Extract numbers (part1)
            String result = string.replaceAll("[a-zA-Z]", "");
            String twoDigitString = String.format("%s%s", result.charAt(0), result.charAt(result.length() - 1));
            extracted.add(Integer.parseInt(twoDigitString));
        }

        Integer sum = 0;
        for (Integer i : extracted) {
            sum += i;
        }

        System.out.println("Final result is: " + sum);
    }
}