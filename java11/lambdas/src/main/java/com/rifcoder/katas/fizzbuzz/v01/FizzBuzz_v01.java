package com.rifcoder.katas.fizzbuzz.v01;

public class FizzBuzz_v01 {
    public static String fizzBuzz(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative numbers are not accepted!");
        }
        String result = "";
        if (number % 3 == 0) {
            result += "Fizz";
        }
        if (number % 5 == 0) {
            result += "Buzz";
        }
        return !result.isEmpty() ? result : Integer.toString(number);
    }
}
