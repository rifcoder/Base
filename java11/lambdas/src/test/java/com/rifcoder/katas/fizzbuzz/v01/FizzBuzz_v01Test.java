package com.rifcoder.katas.fizzbuzz.v01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzz_v01Test {
    @Test
    @DisplayName("Test for 1 and 2")
    void testFor1And2() {
        assertThat(FizzBuzz_v01.fizzBuzz(1)).isEqualTo("1");
        assertThat(FizzBuzz_v01.fizzBuzz(2)).isEqualTo("2");
    }

    @Test
    @DisplayName("Return Fizz to numbers divides by 3")
    void fizzToNumbersDividedBy3() {
        assertThat(FizzBuzz_v01.fizzBuzz(3)).isEqualTo("Fizz");
        assertThat(FizzBuzz_v01.fizzBuzz(6)).isEqualTo("Fizz");
        assertThat(FizzBuzz_v01.fizzBuzz(333)).isEqualTo("Fizz");
    }

    @Test
    @DisplayName("Return Buzz for numbers divides by 5")
    void returnBuzzForNumbersDividesBy5() {
        assertThat(FizzBuzz_v01.fizzBuzz(5)).isEqualTo("Buzz");
        assertThat(FizzBuzz_v01.fizzBuzz(100)).isEqualTo("Buzz");
        assertThat(FizzBuzz_v01.fizzBuzz(1010)).isEqualTo("Buzz");
    }

    @Test
    @DisplayName("Return FizzBuzz for numbers divides by 15")
    void returnFizzBuzzForNumbersDividesBy15() {
        assertThat(FizzBuzz_v01.fizzBuzz(15)).isEqualTo("FizzBuzz");
        assertThat(FizzBuzz_v01.fizzBuzz(105)).isEqualTo("FizzBuzz");
        assertThat(FizzBuzz_v01.fizzBuzz(300)).isEqualTo("FizzBuzz");
    }

    @Test
    @DisplayName("If number is non positive throws exception")
    void ifNumberIsNegativeThrowsException() {
        assertThatThrownBy(() -> FizzBuzz_v01.fizzBuzz(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Non positive numbers are not accepted!");
    }
}