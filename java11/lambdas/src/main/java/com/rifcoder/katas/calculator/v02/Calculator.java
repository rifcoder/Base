package com.rifcoder.katas.calculator.v02;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static int sum(String numbers) {
        Pattern pattern = Pattern.compile("//(.)\\n(.*)");
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.matches()) {
            String delimiter = matcher.group(1);
            System.out.println("Delimiter=[" + delimiter + "]");
            String numbersStrings = matcher.group(2);
            System.out.println("Numbers=[" + numbersStrings + "]");
            return Arrays.stream(numbersStrings.split(delimiter))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .sum();
        } else {
            System.out.println("Default processing...");
            String[] numbersArray = numbers.split(",|(\n)");
            return Arrays.stream(numbersArray)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
    }
}
