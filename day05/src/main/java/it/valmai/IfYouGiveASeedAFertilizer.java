package it.valmai;

import java.util.*;
import java.util.stream.Stream;

public class IfYouGiveASeedAFertilizer {

    public static void main(String[] args) {

        List<String> lines = AocInputReader.getLinesFromInput("test.txt");
        List<List<String>> instructions = AocInputReader.divideList(lines);
        Map<String, List<String>> instructionsMap = new HashMap<>();
        List<Long> seeds = AocInputReader.extractLongs(instructions.get(0).get(0));

        for (int i = 1; i < instructions.size(); i++) {
            String instruction = instructions.get(i).get(0).replace(" map:", "");
            instructionsMap.putIfAbsent(instruction, new ArrayList<>());
            for (int j = 1; j < instructions.get(i).size(); j++) {
                instructionsMap.get(instruction).add(instructions.get(i).get(j));
            }
        }

        // Part 1
        List<Long> locations = allProcess(seeds.parallelStream(), instructionsMap).toList();
        System.out.println("Location seeds is: " + Collections.min(locations));

        // Part 2
        Long minimumLocationPart2 = null;
        for (int i = 0; i < seeds.size(); i += 2) {
            Long currentSeed = seeds.get(i);
            for (int j = 0; j < seeds.get(i + 1); j++) {
                Long processed = allProcess(Optional.of(currentSeed + j).stream(), instructionsMap).findFirst().get();
                if (minimumLocationPart2 == null) {
                    minimumLocationPart2 = processed;
                } else {
                    if (processed < minimumLocationPart2) {
                        minimumLocationPart2 = processed;
                    }
                }
            }
        }
        System.out.println("Location seeds range is: " + minimumLocationPart2);
    }

    private static Stream<Long> allProcess(Stream<Long> stream, Map<String, List<String>> instructionsMap) {
        return stream.map(seed -> processStep(instructionsMap.get("seed-to-soil"), seed))
                .map(soil -> processStep(instructionsMap.get("soil-to-fertilizer"), soil))
                .map(fertilizer -> processStep(instructionsMap.get("fertilizer-to-water"), fertilizer))
                .map(water -> processStep(instructionsMap.get("water-to-light"), water))
                .map(light -> processStep(instructionsMap.get("light-to-temperature"), light))
                .map(temperature -> processStep(instructionsMap.get("temperature-to-humidity"), temperature))
                .map(humidity -> processStep(instructionsMap.get("humidity-to-location"), humidity));
    }

    private static Long processStep(List<String> instructionsMap, Long seed) {
        for (String string : instructionsMap) {
            List<Long> numbers = AocInputReader.extractLongs(string);
            Long d = numbers.get(0);
            Long s = numbers.get(1);
            Long r = numbers.get(2);
            if (seed < (s + r) && seed >= s) {
                return seed - s + d;
            }
        }
        return seed;
    }
}
