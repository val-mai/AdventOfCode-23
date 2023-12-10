package it.valmai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MirageMaintenance {
    public static void main(String[] args) {

        String input = "input.txt";

        // Part 1
        List<List<Long>> history = AocInputReader.getLinesFromInput(input).stream()
                .map(AocInputReader::extractLongs).toList();

        List<Long> forecasts = getForecasts(history);
        long result = forecasts.stream().mapToLong(value -> value).sum();

        System.out.println("Sum of forecasts first part is: " + result);

        // Part 2
        history = AocInputReader.getLinesFromInput(input).stream()
                .map(s -> {
                    List<Long> list = AocInputReader.extractLongs(s);
                    Collections.reverse(list);
                    return list;
                })
                .toList();

        forecasts = getForecasts(history);
        result = forecasts.stream().mapToLong(value -> value).sum();

        System.out.println("Sum of forecasts second part is: " + result);
    }

    private static List<Long> getForecasts(List<List<Long>> history) {
        List<Long> forecasts = new ArrayList<>();
        history.forEach(variableHistory -> {
            List<List<Long>> migrations = new ArrayList<>();
            migrations.add(variableHistory);
            List<Long> migration;
            while (!migrations.get(migrations.size() - 1).stream().allMatch(element -> element == 0L)) {
                List<Long> current = migrations.get(migrations.size() - 1);
                migration = new ArrayList<>();
                for (int i = 0; i < current.size() - 1; i++) {
                    long diff = current.get(i + 1) - current.get(i);
                    migration.add(diff);
                }
                migrations.add(migration);
            }

            migrations.get(migrations.size() - 1).add(0L);
            for (int i = migrations.size() - 1; i > 0; i--) {
                Long lastLongPrevious = migrations.get(i - 1).get(migrations.get(i - 1).size() - 1);
                Long lastLongCurrent = migrations.get(i).get(migrations.get(i).size() - 1);
                migrations.get(i - 1).add(lastLongPrevious + lastLongCurrent);
            }

            List<Long> actual = migrations.get(0);
            forecasts.add(actual.get(actual.size()-1));
        });
        return forecasts;
    }
}