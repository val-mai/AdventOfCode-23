package it.valmai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("input.txt")
                .stream().map(line -> line.replace(".", " ")).toList();

        List<Integer> partNumbers = new ArrayList<>();
        HashMap<Integer, List<Integer>> gears = new HashMap<>();

        for (int line = 0; line < lines.size(); line++) {

            Pattern pattern = Pattern.compile("\\d+");
            Matcher numberMatcher = pattern.matcher(lines.get(line));

            while (numberMatcher.find()) {
                int currentNumber = Integer.parseInt(numberMatcher.group(0));
                neighboursLoop:
                for (int column = numberMatcher.start(); column < numberMatcher.end(); column++) {
                    Integer[][] positions = new Integer[][]{{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
                    for (Integer[] position : positions) {
                        int x = column + position[0];
                        int y = line + position[1];
                        boolean boundary = (x < 0 || x >= lines.get(0).length() || y < 0 || y >= lines.size());
                        if (!boundary) {
                            boolean isSymbol = !Character.isLetterOrDigit(lines.get(y).charAt(x))
                                    && !Character.isWhitespace(lines.get(y).charAt(x));
                            if (isSymbol) {
                                // Get number parts (part 1)
                                partNumbers.add(currentNumber);
                                // Check gears by index (part 2)
                                if (lines.get(y).charAt(x) == '*') {
                                    int gearIndex = y * lines.get(0).length() + x;
                                    List<Integer> gearNumbers = Arrays.asList(0,0);
                                    gears.putIfAbsent(gearIndex, gearNumbers);
                                    if (gears.get(gearIndex).get(0) == 0) {
                                        gears.get(gearIndex).set(0, currentNumber);
                                    } else {
                                        gears.get(gearIndex).set(1, currentNumber);
                                    }
                                    break neighboursLoop;
                                }
                            }
                        }
                    }
                }
            }
        }

        int partSum = 0;
        for (Integer partNumber : partNumbers) {
            partSum += partNumber;
        }

        int gearSum = gears.values().stream()
                .mapToInt(value -> value.get(0) * value.get(1))
                .sum();

        System.out.println("Part numbers sum is " + partSum);
        System.out.println("Gears sum is " + gearSum);
    }
}