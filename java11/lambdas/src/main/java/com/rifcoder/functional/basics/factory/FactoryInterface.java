package com.rifcoder.functional.basics.factory;

@FunctionalInterface
public interface FactoryInterface<T> {
    T create();
}
