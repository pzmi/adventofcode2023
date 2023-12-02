package dev.zmigrodzki;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Day1Test {
    @Test
    void extractCalibrationValue() {
        var actualCalibrationValue = Day1.extractCalibrationValue("a1b2c");
        assertThat(actualCalibrationValue).isEqualTo(12);
    }

    @Test
    void sumAllCalibrationValues() {
        var input = """
                aonebtwoc
                z2x3x
                1two3four
                four95qvkvveight5""";
        var summedAllCalibrationValues = Day1.sumAllCalibrationValues(input);
        assertThat(summedAllCalibrationValues).isEqualTo(12 + 23 + 14 + 45);
    }
}