package com.rifcoder.functional.basics;

import java.util.Objects;

public interface MyFunction<T, R> {
    R apply(T t);

    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
}
