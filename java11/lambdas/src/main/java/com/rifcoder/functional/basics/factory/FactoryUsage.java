package com.rifcoder.functional.basics.factory;

import java.util.function.Supplier;

public class FactoryUsage {
    public static void main(String[] args) {
        FactoryInterface<String> greetFactory = FactoryUsage::greet;
        Supplier<String> greetSupplier = FactoryUsage::greet;

        System.out.println(greetFactory.create());
        System.out.println("This is supplier:" + greetSupplier.get());
    }

    static String greet() {
        return "Hello from factory";
    }
}
