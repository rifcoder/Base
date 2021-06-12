package com.rifcoder.katas.calculator;

import java.util.Arrays;

public class Calculator {
    public int add(String numbers) {
        boolean isCustomDelimiterPresent = numbers.startsWith("//");
        String delimiter = isCustomDelimiterPresent ? readCustomDelimiter(numbers) : ",|(\\n)";
        String expression = isCustomDelimiterPresent ? extractExpression(numbers) : numbers;

        String[] numbersArray = expression.split(delimiter);

        return Arrays.stream(numbersArray)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String extractExpression(String originalString) {
        int endOfDelimiterLine = originalString.indexOf("\\n");
        return originalString.substring(endOfDelimiterLine + 2);
    }

    private String readCustomDelimiter(String originalString) {
        int endOfDelimiterLine = originalString.indexOf("\\n");
        return originalString.substring(2, endOfDelimiterLine);
    }
}
