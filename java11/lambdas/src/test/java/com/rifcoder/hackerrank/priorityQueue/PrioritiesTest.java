package com.rifcoder.hackerrank.priorityQueue;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

class PrioritiesTest {
    private final Priorities priorities = new Priorities();

    @Test
    @DisplayName("Empty list with SERVED events")
    void emptyListWithServedEvents() {
        var events = List.of("SERVED");
        assertThat(priorities.getStudents(events)).isEmpty();
    }

    @Test
    @DisplayName("Single student not served")
    void singleStudentNotServed() {
        var events = List.of("ENTER Jack 4.75 1");
        assertThat(priorities.getStudents(events))
                .contains(new Student(1, "Jack", 4.75));
    }

    @Test
    @DisplayName("Add 3 student, served one of them")
    void add3StudentServedOneOfThem() {
        var events = List.of("ENTER Jack 4.75 1",
                "ENTER Bob 4.9 3",
                "ENTER Jane 4.3 9",
                "SERVED");

        assertThat(priorities.getStudents(events))
                .contains(new Student(1, "Jack", 4.75), Index.atIndex(0))
                .contains(new Student(9, "Jane", 4.3), Index.atIndex(1));
    }

    @Test
    @DisplayName("Add 2 students, served 2 of them")
    void add2StudentsServed2OfThem() {
        var events = List.of("ENTER Jack 4.75 1",
                "ENTER Bob 4.9 3",
                "SERVED",
                "SERVED");
        assertThat(priorities.getStudents(events)).isEmpty();
    }

    @Test
    @DisplayName("Complex test")
    void complexTest() {
        var events = List.of(
        "ENTER John 3.75 50",
        "ENTER Mark 3.8 24",
        "ENTER Shafaet 3.7 35",
        "SERVED",
        "SERVED",
        "ENTER Samiha 3.85 36",
        "SERVED",
        "ENTER Ashley 3.9 42",
        "ENTER Maria 3.6 46",
        "ENTER Anik 3.95 49",
        "ENTER Dan 3.95 50",
        "SERVED");

        assertThat(priorities.getStudents(events))
                .contains(new Student(50, "Dan", 3.95), Index.atIndex(0))
                .contains(new Student(42, "Ashley", 3.9), Index.atIndex(1))
                .contains(new Student(35, "Shafaet", 3.7), Index.atIndex(2))
                .contains(new Student(46, "Maria", 3.6), Index.atIndex(3));

    }

    @Test
    @DisplayName("comparator test")
    void comparatorTest() {
        var events = List.of(
                "ENTER Maria 3.6 46",
                "ENTER Samiha 3.85 36",
                "ENTER Shafaet 3.7 35");

        assertThat(priorities.getStudents(events))
                .contains(new Student(36, "Samiha", 3.85), Index.atIndex(0))
                .contains(new Student(35, "Shafaet", 3.7), Index.atIndex(1))
                .contains(new Student(46, "Maria", 3.6), Index.atIndex(2))
        ;
    }

    @Test
    @DisplayName("Ensure proper student format")
    void ensureProperStudentFormat() {
        var badFormattedEvent = List.of(
                "Illegal Maria 3.6 46"
        );

        assertThatThrownBy(() -> priorities.getStudents(badFormattedEvent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Illegal student entry format");
    }
}