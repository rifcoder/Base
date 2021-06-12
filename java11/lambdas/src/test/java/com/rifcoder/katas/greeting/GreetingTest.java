package com.rifcoder.katas.greeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GreetingTest {
    private Greeting greeting = new Greeting();

    @Test
    @DisplayName("Simple greeting")
    void simpleGreeting() {
        assertThat(greeting.greet("Bob")).isEqualTo("Hello, Bob.");
    }

    @Test
    @DisplayName("Name is null")
    void nameIsNull() {
        assertThat(greeting.greet(null)).isEqualTo("Hello, my friend.");
    }

    @Test
    @DisplayName("Shout name in uppercase")
    void shoutNameInUppercase() {
        assertThat(greeting.greet("JERRY")).isEqualTo("HELLO JERRY!");
    }

    @Test
    @DisplayName("Few names as parameters")
    void fewNamesAsParameters() {
        assertThat(greeting.greet("Jill", "Jane")).isEqualTo("Hello, Jill and Jane.");
    }

    @Test
    @DisplayName("Handle 3+ name parameters")
    void handle3NameParameters() {
        assertThat(greeting.greet("Amy", "Brian", "Charlotte")).isEqualTo("Hello, Amy, Brian, and Charlotte.");
    }

    @Test
    @DisplayName("Mix ordinal and uppercase parameters")
    void mixOrdinalAndUppercaseParameters() {
        assertThat(greeting.greet("Amy", "BRIAN", "Charlotte")).isEqualTo("Hello, Amy and Charlotte. AND HELLO BRIAN!");
    }
}