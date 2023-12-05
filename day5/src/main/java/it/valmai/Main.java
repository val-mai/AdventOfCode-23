package it.valmai;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("input.txt");
        List<List<String>> instructions = divideList(lines);
        Map<String, List<String>> instructionsMap = new HashMap<>();

        List<Long> seeds = extractNumbers(instructions.get(0).get(0));

        for (int i = 1; i < instructions.size(); i++) {
            String instruction = instructions.get(i).get(0).replace(" map:", "");
            instructionsMap.putIfAbsent(instruction, new ArrayList<>());
            for (int j = 1; j < instructions.get(i).size(); j++) {
                instructionsMap.get(instruction).add(instructions.get(i).get(j));
            }
        }

        for (Map.Entry<String, List<String>> entry : instructionsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        List<Long> locations = seeds.stream()
                .map(seed -> processStep(instructionsMap, "seed-to-soil", seed))
                .map(soil -> processStep(instructionsMap, "soil-to-fertilizer", soil))
                .map(fertilizer -> processStep(instructionsMap, "fertilizer-to-water", fertilizer))
                .map(water -> processStep(instructionsMap, "water-to-light", water))
                .map(light -> processStep(instructionsMap, "light-to-temperature", light))
                .map(temperature -> processStep(instructionsMap, "temperature-to-humidity", temperature))
                .map(humidity -> processStep(instructionsMap, "humidity-to-location", humidity))
                .toList();

        System.out.println("Location is: " + Collections.min(locations));
    }

    private static Long processStep(Map<String, List<String>> instructionsMap, String key, Long seed) {
        Map<Long, Long> stepMap = new HashMap<>();
        for (String s : instructionsMap.get(key)) {
            List<Long> instrucionList = extractNumbers(s);
            for (int i = 0; i < instrucionList.get(2); i++) {
                stepMap.putIfAbsent(instrucionList.get(1) + i, instrucionList.get(0) + i);
            }
        }
        return stepMap.getOrDefault(seed, seed);
    }

    private static List<Long> extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.results()
                .map(match -> Long.parseLong(match.group())).toList();
    }

    private static List<List<String>> divideList(List<String> inputList) {
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

}
