package com.rifcoder.katas.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Add empty string")
    void addEmptyString() {
        assertThat(calculator.add("")).as("Should return zero for empty string").isEqualTo(0);
    }

    @Test
    @DisplayName("Calculator simple tests")
    void calculatorSimpleTests() {
        assertThat(calculator.add("1,2,3")).isEqualTo(6);
        assertThat(calculator.add("1,5,1000,10")).isEqualTo(1016);
        assertThat(calculator.add("1,5,-7")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Handle new lines and commas as delimiters")
    void handleNewLinesAndCommasAsDelimiters() {
        assertThat(calculator.add("1\n2,3")).isEqualTo(6);
        assertThat(calculator.add("1\n2,3\n-7")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Custom user delimiter")
    void customUserDelimiter() {
        assertThat(calculator.add("//;\\n1;2")).isEqualTo(3);
        assertThat(calculator.add("//z\\n-1z2z3")).isEqualTo(4);
        assertThat(calculator.add("//z\\n")).isEqualTo(0);
    }
}