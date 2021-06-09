package com.rifcoder.katas.strategy;

import java.util.List;
import java.util.function.Predicate;

public class SimpleStrategy {

    public static int computeSum(List<Integer> list, Predicate<Integer> filter) {
        return list.stream()
                .filter(filter)
                .mapToInt(e -> e)
                .sum();
    }

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(computeSum(list, e -> true));
        System.out.println(computeSum(list, e -> e % 2 == 0));
        System.out.println(computeSum(list, e -> e % 2 != 0));

    }
}
