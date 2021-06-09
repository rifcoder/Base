package com.rifcoder.student.example;

import com.rifcoder.common.Student;
import com.rifcoder.common.generator.CommonLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

/**
 * User: rifcoder
 * Date: 30/04/14
 */
public class OldFasionAnonimousClasses {
    private static Logger logger = LoggerFactory.getLogger(OldFasionAnonimousClasses.class);
    private final List<Student> students;

    public OldFasionAnonimousClasses() {
        this.students = populateStudentsList();
    }

    private void doFilteringExternalIteration() {
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

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    private void doFilteringInnerClasses() {
        double highestScore = 0.0;
        SomeList<Student, Double> myStudents = new SomeList<>(students);
        highestScore = myStudents.filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getGraduatedYear() == 2011;
            }
        }).map(new Mapper<Student, Double>() {
            @Override
            public Double extract(Student student) {
                return student.getScore();
            }
        }).max();

        logger.info("Inner class result. Max score for 2011: {}", highestScore);
    }

    private void doFilteringWithLambda() {
        SomeList<Student, Double> myStudents = new SomeList<>(students);
        double highestScore = myStudents.
                filter(s -> s.getGraduatedYear() == 2011).
                map(Student::getScore).
                max();

        logger.info("Lambda result. Max sore for 2011: {}", highestScore);
    }

    private List<Student> populateStudentsList() {
        return CommonLoader.loadStudents(new File("students.txt"));
    }

    public static void main(String[] args) {
        OldFasionAnonimousClasses instance = new OldFasionAnonimousClasses();
        instance.doFilteringExternalIteration();
        instance.doFilteringInnerClasses();
        instance.doFilteringWithLambda();
    }
}
