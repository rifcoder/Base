package com.rifcoder.katas.calculator.v02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {
    @Test
    @DisplayName("Empty string should return 0")
    void emptyStringShouldReturn0() {
        assertThat(Calculator.sum("")).isEqualTo(0);
    }

    @Test
    @DisplayName("Simple sum for 1 or 2 numbers")
    void simpleSumFor1Or2Numbers() {
        assertThat(Calculator.sum("1")).isEqualTo(1);
        assertThat(Calculator.sum("1,2")).isEqualTo(3);
    }

    @Test
    @DisplayName("Handle unknown amount of numbers")
    void handleUnknownAmountOfNumbers() {
        assertThat(Calculator.sum("1,2,10,5")).isEqualTo(18);
    }

    @Test
    @DisplayName("Handle comma and new line as delimiters")
    void handleCommaAndNewLineAsDelimiters() {
        assertThat(Calculator.sum("1\n2,3")).isEqualTo(6);
    }

    @Test
    @DisplayName("Support different delimiters")
    void supportDifferentDelimiters() {
        assertThat(Calculator.sum("//;\n1;2")).isEqualTo(3);
    }
}
