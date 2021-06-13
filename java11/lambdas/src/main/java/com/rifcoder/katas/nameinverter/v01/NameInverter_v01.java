package com.rifcoder.katas.nameinverter.v01;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameInverter_v01 {

    public static String invert(String input) {
        Objects.requireNonNull(input, "null input is not acceptable!");
        if (input.trim().isEmpty()) {
            return "";
        }

        return invertName(input);
    }

    private static String invertName(String input) {
        String name = input.trim();

        var onlyNamePattern = Pattern.compile("[a-zA-Z\\-]+");
        if (onlyNamePattern.matcher(name).matches()) {
            return name;
        }

        var namePlusSirnamePattern = Pattern.compile("([a-zA-Z\\-]+)\\s+([a-zA-Z\\-]+)");
        var matcher = namePlusSirnamePattern.matcher(name);
        if (matcher.matches()) {
            var firstName = matcher.group(1);
            var lastName = matcher.group(2);
            return String.format("%s, %s", lastName, firstName);
        } else {
            throw new IllegalArgumentException("Incorrect name!" + name);
        }
    }
}
