package it.valmai;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String input = """
                oneight      
                """;

        Map<String, String> numbersMap = Map.of(
                "one", "1",
                "two", "2",
                "three", "3",
                "four", "4",
                "five", "5",
                "six", "6",
                "seven", "7",
                "eight", "8",
                "nine", "9"
        );

        String[] stringList = input.split("\n");

        List<Integer> extracted = new ArrayList<>();

        for (String string : stringList) {

            List<Integer> numbers = new ArrayList<>();

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
                if (!replaced) {
                    break;
                }
            }

            // Extract numbers (part1)
            String[] chars = string.split("");
            for (String aChar : chars) {
                try {
                    Number number = NumberUtils.createNumber(aChar);
                    numbers.add(number.intValue());
                } catch (Exception ignored) {
                }
            }
            if (numbers.size() >= 2) {
                Integer firstNumber = numbers.get(0);
                Integer lastNumber = numbers.get(numbers.size() - 1);
                numbers = List.of(firstNumber, lastNumber);
            } else {
                numbers = List.of(numbers.get(0), numbers.get(0));
            }

            String twoDigitString = String.format("%s%s", numbers.get(0), numbers.get(1));
            extracted.add(Integer.parseInt(twoDigitString));
        }

        Integer somma = 0;
        for (Integer i : extracted) {
            somma += i;
        }

        System.out.println("Il risultato finale è: " + somma);
    }
}