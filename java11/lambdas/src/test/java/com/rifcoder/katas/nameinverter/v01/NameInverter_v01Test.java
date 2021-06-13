package com.rifcoder.katas.nameinverter.v01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NameInverter_v01Test {
    @Test
    @DisplayName("Handle empty input")
    void handleEmptyOrNullInput() {
        assertThat(NameInverter_v01.invert("")).isEqualTo("");
        assertThat(NameInverter_v01.invert("    ")).isEqualTo("");
    }

    @Test
    @DisplayName("Null should throw NullPointerException")
    void nullShouldThrowNullPointerException() {
        assertThatThrownBy(() -> NameInverter_v01.invert(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("null input is not acceptable!");
    }

    @Test
    @DisplayName("Passing only first name")
    void passingOnlyFirstName() {
        assertThat(NameInverter_v01.invert("John")).isEqualTo("John");
        assertThat(NameInverter_v01.invert("   John    ")).isEqualTo("John");
    }

    @Test
    @DisplayName("Invert simple name and surname")
    void invertSimpleNameAndSurname() {
        assertThat(NameInverter_v01.invert("James Bond")).isEqualTo("Bond, James");
        assertThat(NameInverter_v01.invert("Alice May")).isEqualTo("May, Alice");
    }

    @Test
    @DisplayName("Handle complex sirname")
    void handleComplexSirname() {
        assertThat(NameInverter_v01.invert("Jonh Holy-Day")).isEqualTo("Holy-Day, Jonh");
    }

    @Test
    @DisplayName("Not formatted with a lot of spaces")
    void notFormattedWithALotOfSpaces() {
        assertThat(NameInverter_v01.invert("James    Bond")).isEqualTo("Bond, James");
        assertThat(NameInverter_v01.invert("  Alice   May  ")).isEqualTo("May, Alice");
    }

    @Test
    @DisplayName("Honorifics should be omitted")
    void honorificsShouldBeOmitted() {
        assertThat(NameInverter_v01.invert("Mr. John Smith")).isEqualTo("Smith, John");
    }

//    @Test
//    @DisplayName("Postnominals should be in the end")
//    void postnominalsShouldBeInTheEnd() {
//        assertThat(NameInverter_v01.invert("John Smith Sr.")).isEqualTo("Smith, John Sr.");
//    }
}