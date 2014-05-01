package com.rifcoder.common.generator;

import com.rifcoder.common.Person;
import com.rifcoder.common.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * User: rifcoder
 * Date: 01/05/14
 */
public class CommonGenerator {
    private static final String[] firstNames = {"Oleg", "Vlad", "Sergey", "Kostantin", "Boris", "Eugene", "Denis", "Peter",
            "Dmitry", "Maxim", "Lena", "Katerina", "Maria", "Tanya", "Irina", "Anna", "Zhanna", "Kristina"};

    private static final String[] lastNames = {"Pushkin", "Lermontov", "Esenin", "Bulgakov", "Bristol", "Gogol", "Twen",
            "Samoilov", "Mayakovsky", "Tutchev", "Berkov", "Pots", "Maksimov", "Petrov", "Ivanov"};

    private CommonGenerator() {
    }

    public static Student generateStudent() {
        Random random = new Random();
        int number = Math.abs(random.nextInt());

        String firstName = firstNames[number % firstNames.length];
        String lastName = lastNames[number % lastNames.length];
        short graduatedYear = ((short) (2000 + (Math.random() * 17)));

        return new Student(new Person(firstName, lastName), graduatedYear, Math.random());
    }

    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("students.txt")) {
            for (int i = 0; i < 1000; i++) {
                Student student = CommonGenerator.generateStudent();
                writer.write(String.format("%s %s %s %s\n",
                        student.firstName(), student.lastName(), student.getGraduatedYear(), student.getScore()));
            }
        }
    }
}
