package com.rifcoder.streams.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class ImperativeVsDeclarative {
    public static List<String> omitted(final List<String> from, final String omit) {
        ArrayList<String> res = new ArrayList<>();
        for (String string : from) {
            if (!string.equals(omit)) {
                res.add(string);
            }
        }
        return res;
    }

    public static List<String> omittedFunctional(final List<String> from, final String omit) {
        return from.stream()
                .filter(not(omit::equals))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = List.of("Test", "mamba", "cobra", "delta");

        System.out.println(omitted(list, "cobra"));
        System.out.println(omittedFunctional(list, "delta"));
    }
}
