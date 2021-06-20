package com.rifcoder.hackerrank.priorityQueue;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final int id;
    private final String name;
    private final double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(cgpa, student.cgpa) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cgpa);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }

    @Override
    public int compareTo(Student anotherStudent) {
        if (Double.compare(cgpa, anotherStudent.cgpa) == 0) {
            return name.compareTo(anotherStudent.name);
        }
        //reverse order
        return Double.compare(anotherStudent.cgpa, cgpa);
    }
}
