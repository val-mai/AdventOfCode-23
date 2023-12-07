package it.valmai;

import java.util.ArrayList;
import java.util.List;

public class CamelCard {
    public static void main(String[] args) {

        List<List<String>> hands = new ArrayList<>();
        AocInputReader.getLinesFromInput("test.txt")
                .forEach(line -> hands.add(List.of(line.split(" ")[0], line.split(" ")[1])));

        hands.sort(new ListComparator(new CamelCardComparator(), 0).reversed());

        int totalWinning = 0;
        for (int i = 0; i < hands.size(); i++) {
            totalWinning += (i + 1) * Integer.parseInt(hands.get(i).get(1));
        }

        System.out.println("Total winning is: " + totalWinning);

    }
}


