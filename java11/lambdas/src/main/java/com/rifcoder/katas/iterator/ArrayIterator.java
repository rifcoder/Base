package com.rifcoder.katas.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<E> implements Iterator<E> {
    private final E[] array;
    private int position = 0;

    public ArrayIterator(E[] array) {
        this.array = array;
    }

    public boolean hasNext() {
        return position <= array.length - 1;
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There is no more elements in array!");
        }
        return array[position++];
    }
}
