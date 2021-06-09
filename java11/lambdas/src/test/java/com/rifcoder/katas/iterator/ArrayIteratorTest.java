package com.rifcoder.katas.iterator;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArrayIteratorTest {

    @Test
    @DisplayName("Empty array method check")
    void hasNext_empty_array() {
        Integer[] empty = new Integer[0];
        ArrayIterator<Integer> iterator = new ArrayIterator<>(empty);
        assertThat(iterator.hasNext()).as("Underlying array is empty, hasNext should be false").isFalse();
    }

    @Test
    @DisplayName("Single element array check")
    void hasNext_single_item_array() {
        Integer[] singleElement = new Integer[1];
        singleElement[0] = 5;
        ArrayIterator<Integer> iterator = new ArrayIterator<>(singleElement);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(5);
    }

    @Test
    @DisplayName("Filled array method check")
    void hasNext() {
        Integer[] ints = List.of(1, 2, 8).toArray(new Integer[3]);
        ArrayIterator<Integer> iterator = new ArrayIterator<>(ints);

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(8);
        assertThat(iterator.hasNext()).isFalse();
    }
}