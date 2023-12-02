package dev.zmigrodzki;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Day1 {
    private static final Map<String, Integer> spelledAndNumericDigits = spelledAndNumericDigits();

    static Map<String, Integer> spelledAndNumericDigits() {
        var sd = new HashMap<String, Integer>(18);
        sd.put("one", 1);
        sd.put("two", 2);
        sd.put("three", 3);
        sd.put("four", 4);
        sd.put("five", 5);
        sd.put("six", 6);
        sd.put("seven", 7);
        sd.put("eight", 8);
        sd.put("nine", 9);
        sd.put("1", 1);
        sd.put("2", 2);
        sd.put("3", 3);
        sd.put("4", 4);
        sd.put("5", 5);
        sd.put("6", 6);
        sd.put("7", 7);
        sd.put("8", 8);
        sd.put("9", 9);
        return sd;
    }

    static int sumAllCalibrationValues(String lines) {
        return lines.lines()
                .mapToInt(Day1::extractCalibrationValue)
                .sum();
    }

    static Optional<Integer> firstDigit(String a) {
        return a.codePoints()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .boxed()
                .findFirst();
    }

    static Optional<Integer> lastDigit(String a) {
        return a.codePoints()
                .boxed()
                .toList()
                .reversed()
                .stream()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .findFirst();
    }

    static int extractCalibrationValue(String line) {
        var firstDigitCandidates = spelledAndNumericDigits.keySet().stream()
                .map(spelledOrNumericDigit -> Map.entry(spelledOrNumericDigit, line.indexOf(spelledOrNumericDigit)))
                .filter(e -> e.getValue() != -1)
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();

        var lastDigitCandidates = spelledAndNumericDigits.keySet().stream()
                .map(spelledOrNumericDigit -> Map.entry(spelledOrNumericDigit, line.lastIndexOf(spelledOrNumericDigit)))
                .filter(e -> e.getValue() != -1)
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();

        var firstDigit = spelledAndNumericDigits.get(firstDigitCandidates.get(0).getKey());
        var lastDigit = spelledAndNumericDigits.get(lastDigitCandidates.get(lastDigitCandidates.size() - 1).getKey());
        return 10 * firstDigit + lastDigit;
    }
}
