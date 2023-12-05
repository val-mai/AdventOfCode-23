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
            instructionsMap.putIfAbsent(instruction,new ArrayList<>());
            for (int j = 1; j < instructions.get(i).size(); j++) {
                instructionsMap.get(instruction).add(instructions.get(i).get(j));
            }
        }

        for (Map.Entry<String, List<String>> entry : instructionsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<Long, Long> seedToSoil = new HashMap<>();
        Map<Long, Long> soilToFertilizer = new HashMap<>();
        Map<Long, Long> fertilizerToWater = new HashMap<>();
        Map<Long, Long> waterToLight = new HashMap<>();
        Map<Long, Long> lightToTemperature = new HashMap<>();
        Map<Long, Long> temperatureToHumidity = new HashMap<>();
        Map<Long, Long> humidityToLocation = new HashMap<>();

        processInstructions(instructionsMap, "seed-to-soil", seedToSoil);
        processInstructions(instructionsMap, "soil-to-fertilizer", soilToFertilizer);
        processInstructions(instructionsMap, "fertilizer-to-water", fertilizerToWater);
        processInstructions(instructionsMap, "water-to-light", waterToLight);
        processInstructions(instructionsMap, "light-to-temperature", lightToTemperature);
        processInstructions(instructionsMap, "temperature-to-humidity", temperatureToHumidity);
        processInstructions(instructionsMap, "humidity-to-location", humidityToLocation);

        List<Long> locations = seeds.stream()
                .map(seed -> seedToSoil.getOrDefault(seed, seed))
                .map(seed -> soilToFertilizer.getOrDefault(seed, seed))
                .map(seed -> fertilizerToWater.getOrDefault(seed, seed))
                .map(seed -> waterToLight.getOrDefault(seed, seed))
                .map(seed -> lightToTemperature.getOrDefault(seed, seed))
                .map(seed -> temperatureToHumidity.getOrDefault(seed, seed))
                .map(seed -> humidityToLocation.getOrDefault(seed, seed))
                .toList();


        System.out.println("Location is: " + Collections.min(locations));

    }

    private static void processInstructions(Map<String, List<String>> instructionsMap, String key, Map<Long, Long> seedToSoil) {
        for (String s : instructionsMap.get(key)) {
            List<Long> instrucionList = extractNumbers(s);
            for (int i = 0; i < instrucionList.get(2); i++) {
                seedToSoil.putIfAbsent(instrucionList.get(1) + i, instrucionList.get(0) + i);
            }
        }
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
