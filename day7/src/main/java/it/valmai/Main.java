package it.valmai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<List<String>> hands = new ArrayList<>();
        AocInputReader.getLinesFromInput("test.txt")
                .forEach(line -> hands.add(List.of(line.split(" ")[0], line.split(" ")[1])));

        for (List<String> hand : hands) {
            System.out.println(hand);
        }

    }

    static class PokerHandComparator implements Comparator<String> {
        @Override
        public int compare(String hand1, String hand2) {
            // Compare poker hands based on standard hand rankings

            PokerHandType type1 = PokerHandType.detectHandType(hand1);
            PokerHandType type2 = PokerHandType.detectHandType(hand2);

            // Compare based on hand type first
            int typeComparison = Integer.compare(type2.ordinal(), type1.ordinal());
            if (typeComparison != 0) {
                return typeComparison;
            }

            // If the hands have the same type, compare based on specific hand type rules
            return compareFirstCard(hand1,hand2);
        }
    }

    private static int compareFirstCard(String hand1, String hand2) {
        char highestCard1 = hand1.charAt(0);
        char highestCard2 = hand2.charAt(0);
        return Character.compare(highestCard1, highestCard2);
    }
}

enum PokerHandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    private Map<String, String> letterMap = Map.of(
            "T", "A", "J", "B", "Q", "C", "K", "D", "A", "E");

    // Helper method to detect the hand type of a poker hand
    static PokerHandType detectHandType(String hand) {
        if (isFiveOfAKind(hand)) return FIVE_OF_A_KIND;
        if (isFourOfAKind(hand)) return FOUR_OF_A_KIND;
        if (isFourOfAKind(hand)) return FULL_HOUSE;
        if (isThreeOfAKind(hand)) return THREE_OF_A_KIND;
        if (isTwoPair(hand)) return TWO_PAIR;
        if (isOnePair(hand)) return ONE_PAIR;
        return HIGH_CARD;
    }

    // Helper method to check if a hand is a five of a kind
    private static boolean isFiveOfAKind(String hand) {
        // Implement the logic to check for a full house
        return false;
    }

    // Helper method to check if a hand is a four of a kind
    private static boolean isFourOfAKind(String hand) {
        // Implement the logic to check for four of a kind
        return false;
    }

    // Helper method to check if a hand is a four of a kind
    private static boolean isFullHouse(String hand) {
        // Implement the logic to check for full house
        return false;
    }


    // Helper method to check if a hand is three of a kind
    private static boolean isThreeOfAKind(String hand) {
        // Implement the logic to check for three of a kind
        return false;
    }

    // Helper method to check if a hand is two pairs
    private static boolean isTwoPair(String hand) {
        // Implement the logic to check for two pairs
        return false;
    }

    // Helper method to check if a hand is one pair
    private static boolean isOnePair(String hand) {
        // Implement the logic to check for one pair
        return false;
    }
}
