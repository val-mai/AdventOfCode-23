package it.valmai;

import java.math.BigInteger;
import java.util.*;

public class HauntedWasteland {
    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("input.txt");

        String instructions = lines.get(0);
        Map<String, List<String>> map = new HashMap<>();
        lines.subList(2, lines.size()).forEach(s -> {
            var split = s.replace(" ", "").split("=");
            var second = split[1].replace("(", "")
                    .replace(")", "").split(",");
            map.putIfAbsent(split[0], Arrays.asList(second));
        });

        // Part 1
        List<String> startList = map.keySet().stream().filter(s -> s.endsWith("AAA")).toList();
        int result = solve(instructions,map,startList);
        System.out.println("Counter is: " + result);

        // Part 2
        startList = map.keySet().stream().filter(s -> s.endsWith("A")).toList();
        result = solve(instructions,map,startList);
        System.out.println("Ghost counter is: " + result);

    }

    private static int solve(String instructions, Map<String, List<String>> map, List<String> startList) {
        List<Integer> stepsList = new ArrayList<>();
        for (String current : startList) {
            int steps = 0;
            while (!current.endsWith("Z")) {
                char instruction = instructions.charAt(steps % instructions.length());
                int index = instruction == 'L' ? 0 : 1;
                current = map.get(current).get(index);
                steps++;
            }
            stepsList.add(steps);
        }
        return getLcm(stepsList);
    }

    public static int getLcm(List<Integer> stepsPos) {
        BigInteger lcm = BigInteger.valueOf(stepsPos.get(0));
        for (Integer stepsPo : stepsPos) {
            BigInteger currentStep = BigInteger.valueOf(stepsPo);
            lcm = lcm.multiply(currentStep).divide(lcm.gcd(currentStep));
        }
        return lcm.intValue();
    }

}