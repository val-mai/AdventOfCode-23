package it.valmai;

import java.util.*;
import java.util.stream.Stream;

public class IfYouGiveASeedAFertilizer {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("test.txt");
        List<List<String>> instructions = AocInputReader.divideList(lines);
        Map<String, List<String>> instructionsMap = new HashMap<>();
        List<Long> seeds = AocInputReader.extractLongs(instructions.get(0).get(0));
        Long minimumLocationPart2 = null;

        for (int i = 1; i < instructions.size(); i++) {
            String instruction = instructions.get(i).get(0).replace(" map:", "");
            instructionsMap.putIfAbsent(instruction, new ArrayList<>());
            for (int j = 1; j < instructions.get(i).size(); j++) {
                instructionsMap.get(instruction).add(instructions.get(i).get(j));
            }
        }

        for (int i = 0; i < seeds.size(); i += 2) {
            Long currentSeed = seeds.get(i);
            for (int j = 0; j < seeds.get(i + 1); j++) {
                Long processed = allProcess(Optional.of(currentSeed+j).stream(), instructionsMap).findFirst().get();
                if (minimumLocationPart2 == null) {
                    minimumLocationPart2 = processed;
                } else {
                    if (processed < minimumLocationPart2) {
                        minimumLocationPart2 = processed;
                    }
                }
            }
        }
        List<Long> locations = allProcess(seeds.parallelStream(),instructionsMap).toList();

        System.out.println("Location is: " + Collections.min(locations));
        System.out.println("Location is: " + minimumLocationPart2);
    }

    private static Stream<Long> allProcess(Stream<Long> stream, Map<String, List<String>> instructionsMap) {
        return stream.map(seed -> processStep(instructionsMap, "seed-to-soil", seed))
                .map(soil -> processStep(instructionsMap, "soil-to-fertilizer", soil))
                .map(fertilizer -> processStep(instructionsMap, "fertilizer-to-water", fertilizer))
                .map(water -> processStep(instructionsMap, "water-to-light", water))
                .map(light -> processStep(instructionsMap, "light-to-temperature", light))
                .map(temperature -> processStep(instructionsMap, "temperature-to-humidity", temperature))
                .map(humidity -> processStep(instructionsMap, "humidity-to-location", humidity));
    }

    private static Long processStep(Map<String, List<String>> instructionsMap, String key, Long seed) {
        System.out.println("Step :" + key);
        Map<Long, Long> stepMap = new HashMap<>();
        for (String s : instructionsMap.get(key)) {
            List<Long> instructionList = AocInputReader.extractLongs(s);
            for (int i = 0; i < instructionList.get(2); i++) {
                if (instructionList.get(1) + i == seed) {
                    stepMap.putIfAbsent(instructionList.get(1) + i, instructionList.get(0) + i);
                }
            }
        }
        return stepMap.getOrDefault(seed, seed);
    }

}
