package it.valmai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CosmicExpansion {

    public static void main(String[] args) {

        List<List<String>> universe = new ArrayList<>();
        AocInputReader.getLinesFromInput("input.txt").stream()
                .map(line -> Arrays.asList(line.split("")))
                .forEach(universe::add);

        List<Integer[]> galaxies = findGalaxies(universe);

        List<Integer> emptyRows = new ArrayList<>();
        for (int r = 0; r < universe.size(); r++) {
            if (universe.get(r).stream().noneMatch(s -> s.equals("#"))) {
                emptyRows.add(r);
            }
        }
        List<Integer> emptyColumns = new ArrayList<>();
        for (int c = 0; c < universe.get(0).size(); c++) {
            int column = c;
            if (universe.stream().noneMatch(row -> row.get(column).equals("#"))) {
                emptyColumns.add(c);
            }
        }

        int expanseRatio = 2;
        long total = getTotalDistance(galaxies, emptyRows, emptyColumns, expanseRatio);

        System.out.println("Total distance first part is: " + total);

        expanseRatio = 1000000;
        total = getTotalDistance(galaxies, emptyRows, emptyColumns, expanseRatio);

        System.out.println("Total distance second part is: " + total);

    }

    private static long getTotalDistance(List<Integer[]> galaxies, List<Integer> emptyRows, List<Integer> emptyColumns, int expanseRatio) {
        long total = 0L;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = 0; j < i; j++) {
                Integer[] point1 = galaxies.get(i);
                Integer[] point2 = galaxies.get(j);

                int c1 = point1[0], r1 = point1[1];
                int c2 = point2[0], r2 = point2[1];

                long rowDistance = calculateDistance(emptyRows, r1, r2, expanseRatio);
                long columnDistance = calculateDistance(emptyColumns, c1, c2, expanseRatio);
                total +=  rowDistance + columnDistance;
            }
        }
        return total;
    }

    private static long calculateDistance(List<Integer> emptyList, int start, int end, int expanseRatio) {
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        long distance = 0L;

        for (int value = min; value < max; value++) {
            distance += (emptyList.contains(value)) ? expanseRatio : 1;
        }

        return distance;
    }

    private static List<Integer[]> findGalaxies(List<List<String>> universe) {
        List<Integer[]> galaxies = new ArrayList<>();
        for (int c = 0; c < universe.get(0).size(); c++) {
            for (int r = 0; r < universe.size(); r++) {
                if (universe.get(r).get(c).equals("#")) {
                    galaxies.add(new Integer[]{c, r});
                }
            }
        }
        return galaxies;
    }

}