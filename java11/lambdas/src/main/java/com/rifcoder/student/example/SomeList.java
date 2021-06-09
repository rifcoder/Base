package com.rifcoder.student.example;

import com.rifcoder.common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * User: rifcoder
 * Date: 01/05/14
 */
public class SomeList <T extends Student, V extends Number> {
    private List<T> list;
    private Map<T, V> map;

    public SomeList(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public SomeList(List<T> list, Map<T, V> map) {
        this.list = list;
        this.map = map;
    }

    public SomeList<T, V> filter(Predicate<T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (T student : list) {
            if (predicate.test(student)) {
                resultList.add(student);
            }
        }
        return new SomeList<>(resultList);
    }

    public SomeList<T, V> map(Mapper<T, V> mapper) {
        Map<T, V> map = new HashMap<>();
        for (T student : list) {
            map.put(student, mapper.extract(student));
        }
        return new SomeList<>(list, map);
    }

    public V max() {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException("Wrong map argument!");
        }
        V max = null;
        for (V value : map.values()) {
            if (max == null) {
                max = value;
            }
            if (value.doubleValue() > max.doubleValue()) {
                max = value;
            }
        }
        return max;
    }
}
