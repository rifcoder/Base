package com.rifcoder.katas.greeting;

public class Greeting {
    public String greet(String... name) {
        if (name == null) {
            return "Hello, my friend.";
        } else if (name.length == 1 && name[0].toUpperCase().equals(name[0])) {
            return String.format("HELLO %s!", name[0]);
        } else if (name.length == 1) {
            return String.format("Hello, %s.", name[0]);
        } else if (name.length == 2) {
            return String.format("Hello, %s and %s.", name[0], name[1]);
        } else {
            StringBuilder personsString = new StringBuilder();
            for (int i = 0; i < name.length - 1; i++) {
                personsString.append(name[i]).append(", ");
            }
            personsString.append("and ").append(name[name.length - 1]);
            return String.format("Hello, %s.", personsString);
        }
    }
}
