package com.rifcoder.student.example;

/**
 * User: rifcoder
 * Date: 01/05/14
 */
public interface Mapper<T, V> {

    V extract(T object);
}
