package it.valmai;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {

    public static void main(String[] args) {

        List<String> stringList = AocInputReader.getLinesFromInput("input.txt");

        Set<Integer> gamePossible = new HashSet<>();
        List<Integer> setPower = new ArrayList<>();

        for (String s : stringList) {
            Map<String, Integer> maxPerColor = new HashMap<>();
            Integer id = Integer.parseInt(s.split(":")[0].replace("Game ", ""));
            String[] sets = s.split(":")[1].split(";");
            // Extract possible game (part1)
            for (String set : sets) {
                Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)");
                Matcher matcher = pattern.matcher(set);
                while (matcher.find()) {
                    int quantity = Integer.parseInt(matcher.group(1));
                    String color = matcher.group(2);
                    int actual = maxPerColor.getOrDefault(color, 0);
                    if (quantity > actual) {
                        maxPerColor.put(color, quantity);
                    }
                }
            }
            int maxRed = maxPerColor.getOrDefault("red", 1);
            int maxGreen = maxPerColor.getOrDefault("green", 1);
            int maxBlue = maxPerColor.getOrDefault("blue", 1);
            if (maxRed <= 12 && maxGreen <= 13 && maxBlue <= 14) {
                gamePossible.add(id);
            }
            // Calculate power (part2)
            int multiply = maxRed * maxBlue * maxGreen;
            setPower.add(multiply);
        }

        Integer sum = 0;
        for (Integer i : gamePossible) {
            sum += i;
        }
        Integer powerSum = 0;
        for (Integer i : setPower) {
            powerSum += i;
        }

        System.out.println("Game possible result is: " + sum);
        System.out.println("Game power result is: " + powerSum);

    }
}