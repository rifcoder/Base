package com.rifcoder.common;

/**
 * User: rifcoder
 * Date: 30/04/14
 */
public class Student {
    private Person person;
    private int graduatedYear;
    private double score;

    public Student(Person person, short graduatedYear, double score) {
        this.person = person;
        this.graduatedYear = graduatedYear;
        this.score = score;
    }

    public Student(String firstName, String lastName, int graduatedYear, double score) {
        this.person = new Person(firstName, lastName);
        this.graduatedYear = graduatedYear;
        this.score = score;
    }

    public String firstName() {
        return person.getFirstName();
    }

    public String lastName() {
        return person.getLastName();
    }

    public int getGraduatedYear() {
        return graduatedYear;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "person=" + person +
                ", graduatedYear=" + graduatedYear +
                ", score=" + score +
                '}';
    }
}
