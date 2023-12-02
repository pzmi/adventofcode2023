package dev.zmigrodzki;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class Day1 {
    private static final Map<String, Integer> spelledDigits = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);
    public static final int MAX_SPELLED_DIGIT_LENGHT = 5;

    static int sumAllCalibrationValues(String lines) {
        return lines.lines()
                .mapToInt(Day1::extractCalibrationValue)
                .sum();
    }

    static int toDigit(String spelledDigit) {
        return spelledDigits.get(spelledDigit);
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
        return 10 * firstDigit(line).get() + lastDigit(line).get();
    }
}
