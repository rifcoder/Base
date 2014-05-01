package com.rifcoder.student.example;

import com.rifcoder.common.Student;
import com.rifcoder.common.generator.CommonLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * User: rifcoder
 * Date: 30/04/14
 */
public class OldFasionAnonimousClasses {

    private static Logger logger = LoggerFactory.getLogger(OldFasionAnonimousClasses.class);

    public static void main(String[] args) {
        OldFasionAnonimousClasses instance = new OldFasionAnonimousClasses();
        instance.doFiltering();
    }

    private void doFiltering() {
        List<Student> students = populateStudentsList();
        double highestScore = 0d;
        for (Student student : students) {
            if (student.getGraduatedYear() == 2011) {
                if (student.getScore() > highestScore) {
                    highestScore = student.getScore();
                }
            }
        }

        logger.info("Max score for 2011: {}", highestScore);
    }

    private List<Student> populateStudentsList() {
        return CommonLoader.loadStudents(new File("students.txt"));
    }
}
