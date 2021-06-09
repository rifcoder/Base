package com.rifcoder.streams.basics;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamConcatenation {
    public static void main(String[] args) {

        List<String> list1 = List.of("Test", "mamba", "cobra", "delta");
        List<String> list2 = List.of("Bingo", "Bang", "Dang");
        mergedList(list1, list2).forEach(System.out::println);

        Stream<String> s1 = Stream.of("Test", "mamba", "cobra", "delta");
        Stream<String> s2 = Stream.of("Bingo", "Bang", "Dang");
//        mergedStreamBad(s1, s2); //Will be exception thrown

        System.out.println("----------------------------");
        Supplier<Stream<String>> supplier1 = () -> Stream.of("Test", "mamba", "cobra", "delta");
        Supplier<Stream<String>> supplier2 = () -> Stream.of("Bingo", "Bang", "Dang");
        Supplier<Stream<String>> supplier3 = () -> Stream.of("*1*", "*2*");

        List<Supplier<Stream<String>>> suppliers = List.of(supplier1, supplier2, supplier3);

        suppliers.stream()
                .reduce(StreamConcatenation::mergedStreamSupplier)
                .map(Supplier::get)
                .orElse(Stream.of(""))
                .forEach(System.out::println);

    }

    public static Stream<String> mergedList(List<String> first, List<String> second) {
        return first.stream()
                .flatMap(a -> second.stream()
                        .map(b -> a + b));
    }

    public static Stream<String> mergedStreamBad(Stream<String> s1, Stream<String> s2) {
        return s1.flatMap(a -> s2.map(b -> a + b));
    }

    public static Supplier<Stream<String>> mergedStreamSupplier(Supplier<Stream<String>> s1, Supplier<Stream<String>> s2) {
        return () -> s1.get().flatMap(a -> s2.get().map(b -> a + b));
    }
}
