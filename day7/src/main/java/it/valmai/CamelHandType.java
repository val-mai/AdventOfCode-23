package it.valmai;

import java.util.*;

public enum CamelHandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    private static final Map<String, String> letterMap = Map.of(
            "T", "A", "J", ".", "Q", "C", "K", "D", "A", "E");

    // Map for part1
    /*private static final Map<String, String> letterMap = Map.of(
            "T", "A", "J", "B", "Q", "C", "K", "D", "A", "E");*/

    public static List<String> getCards(String hand) {
        List<String> cards = Arrays.asList(hand.split(""));
        return cards.stream().map(c -> letterMap.getOrDefault(c,c)).toList();
    }

    private static List<Long> countCards(String hand) {
        return getCards(hand)
                .stream().map(card -> getCards(hand).stream().filter(s -> s.equals(card)).count()).toList();
    }

    static CamelHandType detectHandType(String hand) {
        if (countCards(hand).contains(5L)) return FIVE_OF_A_KIND;
        if (countCards(hand).contains(4L)) return FOUR_OF_A_KIND;
        if (countCards(hand).contains(3L)) {
            if (countCards(hand).contains(2L)){
                return FULL_HOUSE;
            } else return THREE_OF_A_KIND;
        }
        if (countCards(hand).stream().filter(aLong -> aLong==2L).count() == 4L) return TWO_PAIR;
        if (countCards(hand).contains(2L)) return ONE_PAIR;
        return HIGH_CARD;
    }

    static CamelHandType detectHandTypeWithJoker(String hand) {
        if (getCards(hand).contains(".")) {
            List<String>result = new ArrayList<>();
            List<String> x = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "Q", "K", "A");
            x.forEach(s -> result.add(hand.replaceAll("J",s)));
            result.sort(new CamelCardComparator());
            return detectHandType(result.get(0));
        } else return detectHandType(hand);
    }

}
