package dev.zmigrodzki;

public class Main {
    public static void main(String[] args) {
        day3part2();
    }

    private static void day1() {
        var input = InputReader.fromResource("/day1.txt");
        System.out.println(Day1.sumAllCalibrationValues(input));
    }

    private static void day2part1() {
        var input = InputReader.fromResource("/day2.txt");
        System.out.println(Day2.sumIdsOfPossibleGames(Day2.parseGames(input), new Day2.CubeSet(12, 13, 14)));
    }

    private static void day2part2() {
        var input = InputReader.fromResource("/day2.txt");
        System.out.println(Day2.sumPowers(Day2.parseGames(input)));
    }

    private static void day3part1() {
        var input = InputReader.fromResource("/day3.txt");
        System.out.println(Day3.sumAllPartNumbers(input));
    }

    private static void day3part2() {
        var input = InputReader.fromResource("/day3.txt");
        System.out.println(Day3.sumAllGearRatios(input));
    }
}