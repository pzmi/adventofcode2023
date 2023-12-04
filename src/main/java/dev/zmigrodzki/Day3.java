package dev.zmigrodzki;

import java.util.List;

public class Day3 {

    record Location(int row, int col) {
    }

    record PartNumber(int number, Location start, Location end) {
    }

    static int sumAllPartNumbers(String input) {
        var schematic = parseSchematics(input);

        int sum = 0;
        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {
                if (Character.isDigit(schematic[row][col])) {
                    int startNumber = col;
                    int endNumber = col;
                    for (int k = startNumber; k < schematic[row].length; k++) {
                        if (Character.isDigit(schematic[row][k])) {
                            endNumber = k;
                        } else {
                            col = endNumber;
                            break;
                        }
                    }
                    for (int k = startNumber; k <= endNumber; k++) {
                        if (isAdjacentToPart(schematic, row, k)) {
                            sum += Integer.parseInt(String.copyValueOf(schematic[row], startNumber, endNumber - startNumber + 1));
                            break;
                        }
                    }
                }
            }
        }

        return sum;
    }

    static long sumAllGearRatios(String input) {
        var schematic = parseSchematics(input);

        long sum = 0;
        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {
                if (schematic[row][col] == '*') {
                    var isMaybeGear = neighboursOf(row, col).stream()
                            .allMatch(l -> Character.isDigit(schematic[l.row()][l.col()]) || schematic[l.row()][l.col()] == '.');

                    if (!isMaybeGear) {
                        continue;
                    }

                    var adjacentPartNumbers = neighboursOf(row, col).stream()
                            .filter(l -> Character.isDigit(schematic[l.row()][l.col()]))
                            .map(l -> parsePartNumber(schematic, l.row(), l.col()))
                            .distinct()
                            .toList();
                    if (adjacentPartNumbers.size() == 2) {
                        sum += adjacentPartNumbers.get(0).number() * adjacentPartNumbers.get(1).number();
                    }
                }
            }
        }

        return sum;
    }

    private static PartNumber parsePartNumber(char[][] schematic, int row, int col) {
        int startNumber = col;
        int endNumber = col;
        for (int k = startNumber; k < schematic[row].length; k++) {
            if (Character.isDigit(schematic[row][k])) {
                endNumber = k;
            } else {
                break;
            }
        }
        for (int k = startNumber; k >=0 ; k--) {
            if (Character.isDigit(schematic[row][k])) {
                startNumber = k;
            } else {
                break;
            }
        }
        var number = Integer.parseInt(String.copyValueOf(schematic[row], startNumber, endNumber - startNumber + 1));
        return new PartNumber(number, new Location(row, startNumber), new Location(row, endNumber));
    }

    private static char[][] parseSchematics(String input) {
        return input.lines().map(String::toCharArray).toArray(char[][]::new);
    }

    private static boolean isAdjacentToPart(char[][] schematic, int row, int column) {
        return neighboursOf(row, column).stream()
                .filter(l -> inRange(schematic, l.row, l.col))
                .map(l -> schematic[l.row][l.col])
                .anyMatch(c -> !Character.isDigit(c) && c != '.');
    }

    private static boolean inRange(char[][] matrix, int row, int col) {
        var maxRow = matrix.length - 1;
        var maxCol = matrix[0].length - 1;

        return row >= 0 && col >= 0 && row <= maxRow && col < maxCol;
    }

    private static List<Location> neighboursOf(int row, int col) {
        return List.of(
                new Location(row - 1, col - 1), // top left
                new Location(row, col - 1), // top
                new Location(row + 1, col - 1), // top right
                new Location(row + 1, col), // right
                new Location(row + 1, col + 1),  // bottom right
                new Location(row, col + 1), // bottom
                new Location(row - 1, col + 1));  // bottom
    }
}
