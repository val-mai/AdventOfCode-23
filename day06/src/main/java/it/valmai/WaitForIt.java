package it.valmai;

import java.util.ArrayList;
import java.util.List;

public class WaitForIt {

    public static void main(String[] args) {

        String input = "test.txt";

        List<List<Long>> lines = AocInputReader.getLinesFromInput(input).stream()
                .map(AocInputReader::extractLongs).toList();
        Long totalMarginFirst = getMargins(lines);

        // Part 2
        List<List<Long>> lines2 = AocInputReader.getLinesFromInput(input).stream()
                .map(s -> s.replace(" ",""))
                .map(AocInputReader::extractLongs).toList();

        Long totalMarginSecond = getMargins(lines2);

        System.out.println("Total margin (part1) is: " + totalMarginFirst);
        System.out.println("Total margin (part2) is: " + totalMarginSecond);
    }

    private static Long getMargins(List<List<Long>> lines) {
        List<Long> time = lines.get(0);
        List<Long> distanceRecord = lines.get(1);
        List<Long> margins = new ArrayList<>();
        for (int i = 0; i < time.size(); i++) {
            List<Long> distances = new ArrayList<>();
            for (int j = 0; j <= time.get(i); j++) {
                Long distance = j * ((time.get(i)) - j);
                if (distance>distanceRecord.get(i)) {
                    distances.add(distance);
                }
            }
            margins.add((long) distances.size());
        }
        return margins.stream().reduce(1L, (a, b) -> a * b);
    }

}