package com.rifcoder.hackerrank.balancedParentheses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.rifcoder.hackerrank.balancedParentheses.BalancedParentheses.ensureBalanced;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BalancedParenthesesTest {
    @Test
    @DisplayName("Check empty string")
    void checkEmptyString() {
        assertThat(ensureBalanced("")).isTrue();
    }

    @Test
    @DisplayName("Simple cases with parentheses")
    void simpleCaseWithParentheses() {
        assertThat(ensureBalanced("()")).isTrue();
        assertThat(ensureBalanced("(())")).isTrue();
    }

    @Test
    @DisplayName("Simple unbalanced test")
    void simpleUnbalancedTest() {
        assertThat(ensureBalanced("(")).isFalse();
        assertThat(ensureBalanced("(((")).isFalse();
        assertThat(ensureBalanced(")")).isFalse();
        assertThat(ensureBalanced("())")).isFalse();
    }
}