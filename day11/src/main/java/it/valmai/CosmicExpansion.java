package it.valmai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CosmicExpansion {

    public static void main(String[] args) {

        List<List<String>> universe = new ArrayList<>();
        AocInputReader.getLinesFromInput("test.txt").stream()
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