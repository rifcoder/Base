package com.rifcoder.streams.basics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFlatMapEncapsulation {
    public static void main(String[] args) {
        List<String> result = Stream.of("one", "two", "three")
                .flatMapToInt(word -> word.chars())
                .filter(ch -> ch != 'o')
                .peek(System.out::println)
                .mapToObj(ch -> "[" + ((char) ch) + "]")
                .collect(Collectors.toList());

        result.forEach(System.out::println);

        System.out.println("--------------------------------");
        Stream.of("one", "two", "three")
                .flatMap(word -> word.chars()
                    .filter(ch -> ch != 'o')
                    .peek(System.out::println)
                    .mapToObj(ch -> "[" + ((char) ch) + "]"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        Stream.of("one", "two", "three")
                .flatMap(StreamFlatMapEncapsulation::process)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        int answer = 42;
        new Thread(() -> System.out.println("The answer is: " + answer)).start();
    }

    private static Stream<String> process(String word) {
        return word.chars()
                .filter(ch -> ch != 'o')
                .peek(System.out::println)
                .mapToObj(ch -> "[" + ((char) ch) + "]");
    }
}
