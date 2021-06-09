package com.rifcoder.streams.basics;

import com.rifcoder.functional.basics.factory.FactoryInterface;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SimpleStreamsPlayground {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(4);
        integers.add(111);
        integers.add(15);

//        integers.stream()
//                .filter(number -> number > 10)
//                .peek(System.out::println)
//                .

        Supplier<ArrayList<Long>> supplier = ArrayList::new;
        System.out.println(supplier.get());
    }
}
