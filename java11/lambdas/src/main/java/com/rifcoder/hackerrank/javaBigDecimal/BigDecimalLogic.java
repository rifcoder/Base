package com.rifcoder.hackerrank.javaBigDecimal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class BigDecimalLogic {

    private static final Comparator<Pair> comparator =
            Comparator.comparing(Pair::getNumber).reversed();

    public static String[] sort(String[] inputArray) {
        return Arrays.stream(inputArray)
                .map(Pair::parseFromString)
                .sorted(comparator)
                .map(Object::toString)
                .toArray(String[]::new);
    }
}

class Pair {
    private final BigDecimal number;
    private final String formattedString;

    private Pair(BigDecimal number, String formattedString) {
        this.number = number;
        this.formattedString = formattedString;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public String getFormattedString() {
        return formattedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair tuple = (Pair) o;
        return number.equals(tuple.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return formattedString;
    }

    public static Pair parseFromString(String string) {
        return new Pair(new BigDecimal(string), string);
    }
}
