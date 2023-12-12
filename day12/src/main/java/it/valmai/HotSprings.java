package it.valmai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotSprings {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("input.txt");

        long totalCombinations = 0;
        for (String line : lines) {

            String springs = line.split(" ")[0];
            List<Integer> numbers = Arrays.stream(line.split( " ")[1].split(","))
                    .map(Integer::parseInt).toList();

            List<List<Integer>> combinations = generateCombinations(springs);

            for (List<Integer> combination : combinations) {
                if (isValid(combination, numbers)) {
                    totalCombinations++;
                }
            }
        }

        System.out.println("Total possible combinations is: " + totalCombinations);
    }

    private static List<List<Integer>> generateCombinations(String inputStr) {
        List<List<Integer>> result = new ArrayList<>();
        recursiveCombinations(new ArrayList<>(), inputStr, result);
        return result;
    }

    private static void recursiveCombinations(List<Integer> currentCombination, String remaining, List<List<Integer>> result) {
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        char currentChar = remaining.charAt(0);

        if (currentChar == '#' || currentChar == '.') {
            currentCombination.add(currentChar == '#' ? 1 : 0);
            recursiveCombinations(currentCombination, remaining.substring(1), result);
            currentCombination.remove(currentCombination.size() - 1);
        } else if (currentChar == '?') {
            for (int i = 0; i <= 1; i++) {
                currentCombination.add(i);
                recursiveCombinations(currentCombination, remaining.substring(1), result);
                currentCombination.remove(currentCombination.size() - 1);
            }
        } else {
            currentCombination.add(Character.getNumericValue(currentChar));
            recursiveCombinations(currentCombination, remaining.substring(1), result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> line, List<Integer> targetRuns) {
        List<Integer> runs = new ArrayList<>();
        int n = line.size(), i = 0;
        while (i < n) {
            int c = 0;
            while (i < n && line.get(i++) == 1) c++;
            if (c > 0) {
                runs.add(c);
            }
        }
        return runs.equals(targetRuns);
    }
}