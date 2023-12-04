package it.valmai;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scratchcards {

    public static void main(String[] args) {
        List<String> cards = AocInputReader.getLinesFromInput("input.txt");
        List<Integer> points = new ArrayList<>();
        List<Integer> scratchcards = new ArrayList<>();

        for (String card : cards) {
            int currentLine = Integer.parseInt(card.split(":")[0].replaceAll("\\D", ""));
            String[] numbersSet = card.split(":")[1].split("\\|");

            List<Integer> winningNumbers = extractNumbers(numbersSet[0]);
            List<Integer> cardNumbers = extractNumbers(numbersSet[1]);

            List<Integer> matching = cardNumbers.stream().filter(winningNumbers::contains).toList();
            if (!matching.isEmpty()) points.add((int) Math.pow(2, matching.size() - 1));

            // Add scratch cards (part 2)
            scratchcards.add(currentLine);
            for (int i = currentLine + 1; i <= currentLine + matching.size(); i++) {
                int occurrences = scratchcards.stream().filter(number -> number == currentLine).toList().size();
                for (int j = 0; j < occurrences; j++) {
                    scratchcards.add(i);
                }
            }
        }

        int pointSum = points.stream().mapToInt(Integer::intValue).sum();

        System.out.println("Points sum is: " + pointSum);
        System.out.println("Card total is: " + scratchcards.size());
    }

    private static List<Integer> extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.results().map(match -> Integer.parseInt(match.group())).toList();
    }
}