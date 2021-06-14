package com.rifcoder.katas.nameinverter.v01;

import java.util.List;
import java.util.Objects;

public class NameInverter_v01 {
    private static final int ONLY_FIRSTNAME_PRESENT = 1;
    private static final int FIRSTNAME_AND_LASTNAME_PRESENT = 2;
    private static final int COMPLEX_NAME = 3;

    public static String invert(String input) {
        Objects.requireNonNull(input, "null input is not acceptable!");
        if (isEmpty(input)) {
            return "";
        }

        return invertName(input.trim());
    }

    private static boolean isEmpty(String input) {
        return input.trim().isEmpty();
    }

    private static String invertName(String input) {
        var nameParts = splitIntoParts(input);

        switch (nameParts.length) {
            case ONLY_FIRSTNAME_PRESENT:
                return input;
            case FIRSTNAME_AND_LASTNAME_PRESENT:
                return formatName(nameParts[0], nameParts[1]);
            case COMPLEX_NAME:
                return formatComplexName(nameParts);
            default:
                throw new IllegalArgumentException("Illegal name structure!" + input);
        }

    }

    private static String[] splitIntoParts(String input) {
        return input.split("\\s+");
    }

    private static String formatComplexName(String[] nameParts) {
        String firstPart = nameParts[0];
        String secondPart = nameParts[1];
        String thirdPart = nameParts[2];
        if (Honorifics.isHonorificPresent(firstPart)) {
            return formatName(secondPart, thirdPart);
        } else if (Postnominals.isPostnominalsPresent(thirdPart)) {
            String formattedName = formatName(firstPart, secondPart);
            String postnominal = thirdPart;
            return String.format("%s %s", formattedName, postnominal);
        }
        throw new IllegalArgumentException(
                String.format("Illegal name structure! [%s %s %s]",
                        firstPart,
                        secondPart,
                        thirdPart));
    }

    private static String formatName(String firstName, String lastName) {
        return String.format("%s, %s", lastName, firstName);
    }
}

class Honorifics {
    private static final List<String> honorifics = List.of("Mr.", "Ms.");

    public static boolean isHonorificPresent(String namePart) {
        return honorifics.contains(namePart);
    }
}

class Postnominals {
    private static final List<String> postnominals = List.of("Sr.");

    public static boolean isPostnominalsPresent(String namePart) {
        return postnominals.contains(namePart);
    }
}
