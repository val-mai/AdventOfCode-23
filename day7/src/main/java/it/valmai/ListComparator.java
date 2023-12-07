package it.valmai;

import java.util.Comparator;
import java.util.List;

class ListComparator implements Comparator<List<String>> {
    private final Comparator<String> elementComparator;
    private final int index;

    public ListComparator(Comparator<String> elementComparator, int index) {
        this.elementComparator = elementComparator;
        this.index = index;
    }

    @Override
    public int compare(List<String> list1, List<String> list2) {
        String value1 = list1.get(index);
        String value2 = list2.get(index);
        return elementComparator.compare(value1, value2);
    }
}
