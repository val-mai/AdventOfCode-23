package it.valmai;

import java.util.Comparator;

public class CamelCardComparator implements Comparator<String> {
    @Override
    public int compare(String hand1, String hand2) {



        CamelHandType type1 = CamelHandType.detectHandTypeWithJoker(hand1);
        CamelHandType type2 = CamelHandType.detectHandTypeWithJoker(hand2);

        // For part 1
        /*CamelHandType type1 = CamelHandType.detectHandType(hand1);
        CamelHandType type2 = CamelHandType.detectHandType(hand2);*/

        int typeComparison = Integer.compare(type2.ordinal(), type1.ordinal());
        if (typeComparison != 0) {
            return typeComparison;
        }
        return compareFirstCard(hand1,hand2);
    }

    private int compareFirstCard(String hand1, String hand2) {
        char highestCard1 = CamelHandType.getCards(hand1).get(0).charAt(0);
        char highestCard2 = CamelHandType.getCards(hand2).get(0).charAt(0);
        for (int i = 0; i < 5; i++) {
            highestCard1 = CamelHandType.getCards(hand1).get(i).charAt(0);
            highestCard2 = CamelHandType.getCards(hand2).get(i).charAt(0);
            if (highestCard1!=highestCard2) break;
        }
        return Character.compare(highestCard2, highestCard1);
    }

}
