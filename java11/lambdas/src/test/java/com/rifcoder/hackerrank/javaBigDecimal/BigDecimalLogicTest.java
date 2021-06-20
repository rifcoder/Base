package com.rifcoder.hackerrank.javaBigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.rifcoder.hackerrank.javaBigDecimal.BigDecimalLogic.sort;
import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalLogicTest {

    @Test
    @DisplayName("Sort empty array")
    void sortEmptyArray() {
        String[] emptyArray = new String[0];
        assertThat(sort(emptyArray)).isEmpty();
    }

    @Test
    @DisplayName("Sort array of single input")
    void sortArrayOfSingleInput() {
        assertThat(sort(new String[]{"1"})).isEqualTo(new String[]{"1"});
    }

    @Test
    @DisplayName("Sort simple array")
    void sortSimpleArray() {
        assertThat(sort(new String[]{"1", "3", "2"}))
                .isEqualTo(new String[]{"3", "2", "1"});
    }

    @Test
    @DisplayName("Sort float point numbers")
    void sortFloatPointNumbers() {
        assertThat(sort(new String[]{"0.1", "100.15", "0.999999999"}))
                .isEqualTo(new String[]{"100.15", "0.999999999", "0.1"});
    }

    @Test
    @DisplayName("Sort and print numbers in given format without shortening")
    void sortAndPrintNumbersInGivenFormatWithoutShortening() {
        assertThat(sort(new String[]{"1.0000", "10.1500", "0.0000"}))
                .isEqualTo(new String[]{"10.1500", "1.0000", "0.0000"});

        Arrays.stream(sort(new String[]{"1.0000", "10.1500", "0.0000"}))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Maintain order during process of sort")
    void maintainOrderDuringProcessOfSort() {
        assertThat(sort(new String[]{"0.0", "1", "15", "0", "1.0"}))
                .isEqualTo(new String[]{"15", "1", "1.0", "0.0", "0"});

    }

    @Test
    @DisplayName("Complex sort test")
    void complexSortTest() {
        var toSort = new String[]{
                "-100",
                "50",
                "0",
                "56.6",
                "90",
                "0.12",
                ".12",
                "02.34",
                "000.000"};
        var resultAfterSort = new String[]{
                "90",
                "56.6",
                "50",
                "02.34",
                "0.12",
                ".12",
                "0",
                "000.000",
                "-100"};
        assertThat(sort(toSort)).isEqualTo(resultAfterSort);

    }
}