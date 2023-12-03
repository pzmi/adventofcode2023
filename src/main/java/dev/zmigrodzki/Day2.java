package dev.zmigrodzki;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day2 {
    record CubeSet(int red, int green, int blue) {
    }

    record Game(int id, List<CubeSet> cubeSets) {
    }

    /**
     * @param input example "8 green, 6 blue, 20 red"
     */
    static CubeSet parseCubeSet(String input) {
        var cubeNumbers = input.split(",");
        var red = 0;
        var green = 0;
        var blue = 0;
        for (String cubeNumber : cubeNumbers) {
            var trimmed = cubeNumber.trim();
            var numberAndColor = trimmed.split(" ");
            switch (numberAndColor[1]) {
                case "red" -> red = Integer.parseInt(numberAndColor[0]);
                case "green" -> green = Integer.parseInt(numberAndColor[0]);
                case "blue" -> blue = Integer.parseInt(numberAndColor[0]);
            }
        }
        return new CubeSet(red, green, blue);
    }

    /**
     * @param input example "8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
     */
    static List<CubeSet> parseCubeSets(String input) {
        return Arrays.stream(input.split(";")).map(Day2::parseCubeSet).toList();
    }

    static int parseGameId(String input) {
        return Integer.parseInt(input.split("Game ")[1]);
    }

    static Game parseGame(String input) {
        var gameIdAndCubeSets = input.split(":");
        var gameId = parseGameId(gameIdAndCubeSets[0]);
        var cubeSets = parseCubeSets(gameIdAndCubeSets[1]);

        return new Game(gameId, cubeSets);
    }

    static List<Game> parseGames(String input) {
        return input.lines().map(Day2::parseGame).toList();
    }

    static CubeSet minimalRequiredCubeset(Collection<CubeSet> cubeSets) {
        return cubeSets.stream()
                .reduce(
                        new CubeSet(0, 0, 0),
                        (acc, cubeSet) ->
                                new CubeSet(
                                        Math.max(acc.red(), cubeSet.red()),
                                        Math.max(acc.green(), cubeSet.green()),
                                        Math.max(acc.blue(), cubeSet.blue())
                                )
                );
    }

    static boolean isGamePossible(Game game, CubeSet usedCubeset) {
        var minimalRequiredCubeset = minimalRequiredCubeset(game.cubeSets);
        return minimalRequiredCubeset.red() <= usedCubeset.red() &&
                minimalRequiredCubeset.green() <= usedCubeset.green() &&
                minimalRequiredCubeset.blue() <= usedCubeset.blue();
    }

    static int powerOf(CubeSet cubeSet) {
        return cubeSet.red() * cubeSet.green() * cubeSet.blue();
    }

    static int sumPowers(Collection<Game> games) {
        return games.stream()
                .map(Game::cubeSets)
                .map(Day2::minimalRequiredCubeset)
                .mapToInt(Day2::powerOf)
                .sum();
    }

    static int sumIdsOfPossibleGames(Collection<Game> games, CubeSet usedCubeset) {
        return games.stream()
                .filter(game -> Day2.isGamePossible(game, usedCubeset))
                .mapToInt(Game::id)
                .sum();
    }
}
