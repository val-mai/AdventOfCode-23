package it.valmai;

import java.util.Arrays;
import java.util.List;

public class HotSprings {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("test.txt");

        long totalCombinations = 0;
        for (String line : lines) {

            String springs = line.split(" ")[0];
            List<Integer> numbers = Arrays.stream(line.split( " ")[1].split(","))
                    .map(Integer::parseInt).toList();

            totalCombinations += calculateCombinations(springs, numbers);

            System.out.println(springs + " " + numbers);
            System.out.println(calculateCombinations(springs, numbers));
        }

        System.out.println("Total possible combinations is: " + totalCombinations);
    }

    private static long calculateCombinations(String springs, List<Integer> numbers) {

        if (springs.isEmpty()) return (numbers.isEmpty()) ? 1 : 0;

        if (numbers.isEmpty()) return (springs.contains("#")) ? 0 : 1;

        return 5;

    }
}