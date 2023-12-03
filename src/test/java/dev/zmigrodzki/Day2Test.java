package dev.zmigrodzki;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void shouldParseCubeSet() {
        var parsedCubeSet = Day2.parseCubeSet("8 green, 6 blue, 20 red");
        assertThat(parsedCubeSet).isEqualTo(new Day2.CubeSet(20, 8, 6));
    }

    @Test
    void shouldParseCubeSets() {
        var parsedCubeSets = Day2.parseCubeSets("8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        assertThat(parsedCubeSets).containsExactly(
                new Day2.CubeSet(20, 8, 6),
                new Day2.CubeSet(4, 13, 5),
                new Day2.CubeSet(1, 5, 0)
        );
    }

    @Test
    void shouldParseGameId() {
        var parsedGameId = Day2.parseGameId("Game 21");
        assertThat(parsedGameId).isEqualTo(21);
    }

    @Test
    void shouldParseGame() {
        var parsedGame = Day2.parseGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        assertThat(parsedGame).isEqualTo(
                new Day2.Game(3, List.of(
                        new Day2.CubeSet(20, 8, 6),
                        new Day2.CubeSet(4, 13, 5),
                        new Day2.CubeSet(1, 5, 0)
                )));
    }

    @Test
    void shouldParseGames() {
        var parsedGames = Day2.parseGames("""
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                """);

        assertThat(parsedGames).isEqualTo(
                List.of(
                        new Day2.Game(2,
                                List.of(
                                        new Day2.CubeSet(0, 2, 1),
                                        new Day2.CubeSet(1, 3, 4),
                                        new Day2.CubeSet(0, 1, 1)
                                )
                        ),
                        new Day2.Game(3,
                                List.of(
                                        new Day2.CubeSet(20, 8, 6),
                                        new Day2.CubeSet(4, 13, 5),
                                        new Day2.CubeSet(1, 5, 0)
                                )
                        )

                ));


    }

    @Test
    void shouldReturnFalseWhenGameHasMoreCubesThanExpectedCubeSet() {
        var game = new Day2.Game(2,
                List.of(
                        new Day2.CubeSet(0, 1, 2),
                        new Day2.CubeSet(1, 4, 3),
                        new Day2.CubeSet(0, 1, 1)
                )
        );
        var usedCubeSet = new Day2.CubeSet(1, 1, 1);

        var isPossible = Day2.isGamePossible(game, usedCubeSet);

        assertThat(isPossible).isFalse();
    }

    @Test
    void shouldReturnTrueWhenGameHasLessCubesThanExpectedCubeSet() {
        var game = new Day2.Game(2,
                List.of(
                        new Day2.CubeSet(0, 1, 2),
                        new Day2.CubeSet(1, 4, 3),
                        new Day2.CubeSet(0, 1, 1)
                )
        );
        var usedCubeSet = new Day2.CubeSet(10, 10, 10);

        var isPossible = Day2.isGamePossible(game, usedCubeSet);

        assertThat(isPossible).isTrue();
    }

    @Test
    void shouldReturnTrueWhenGameHasEqualNumberCubesToExpectedCubeSet() {
        var game = new Day2.Game(2,
                List.of(
                        new Day2.CubeSet(0, 1, 2),
                        new Day2.CubeSet(1, 4, 3),
                        new Day2.CubeSet(0, 1, 1)
                )
        );
        var usedCubeSet = new Day2.CubeSet(1, 4, 3);

        var isPossible = Day2.isGamePossible(game, usedCubeSet);

        assertThat(isPossible).isTrue();
    }
}