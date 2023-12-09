package it.valmai;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AocInputReader {

    public static List<String> getLinesFromInput(String input) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<List<String>> divideList(List<String> inputList) {
        List<List<String>> resultLists = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        for (String line : inputList) {
            if (line.isEmpty()) {
                if (!currentList.isEmpty()) {
                    resultLists.add(currentList);
                    currentList = new ArrayList<>();
                }
            } else {
                currentList.add(line);
            }
        }
        if (!currentList.isEmpty()) {
            resultLists.add(currentList);
        }
        return resultLists;
    }

    public static List<Long> extractLongs(String input) {
        Pattern pattern = Pattern.compile("[-+]?\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.results()
                .map(match -> Long.parseLong(match.group()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Integer> extractIntegers(String input) {
        Pattern pattern = Pattern.compile("[-+]?\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.results()
                .map(match -> Integer.parseInt(match.group()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
