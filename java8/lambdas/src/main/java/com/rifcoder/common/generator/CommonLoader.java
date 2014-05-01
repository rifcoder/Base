package com.rifcoder.common.generator;

import com.rifcoder.common.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rifcoder
 * Date: 01/05/14
 */
public class CommonLoader {
    private static Logger logger = LoggerFactory.getLogger(CommonLoader.class);

    public static List<Student> loadStudents(File file) {
        List<Student> studentList = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    studentList.add(loadStudent(line));
                }
            }
        } catch (IOException e) {
            logger.error("There was error during the file processing", e);
        }
        return studentList;
    }

    protected static Student loadStudent(String studentString) {
        if (studentString == null || studentString.isEmpty()) {
            throw new IllegalArgumentException("Student cannot be empty or null!");
        }
        String[] split = studentString.split(" ");
        String firstName = split[0];
        String lastName = split[1];
        int graduatedYear = Integer.parseInt(split[2]);
        double score = Double.parseDouble(split[3]);
        return new Student(firstName, lastName, graduatedYear, score);
    }
}
