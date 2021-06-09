package com.rifcoder.common.generator;

import com.rifcoder.common.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CommonLoaderTest {
    public static final double DELTA = 1e-10;

    @Test
    public void testStudentParser() {
        String studentString = studentStringFixture();
        Student loadedStudent = CommonLoader.loadStudent(studentString);

        assertThat(loadedStudent.firstName()).as("First name is wrong!").isEqualTo("Petr");
        assertThat(loadedStudent.lastName()).as("Last name is wrong!").isEqualTo("Pupkin");
        assertThat(loadedStudent.getGraduatedYear()).as("Graduated year is wrong!").isEqualTo(2011);
        assertThat(loadedStudent.getScore()).as("Score is wrong!").isEqualTo(0.345, withPrecision(DELTA));
    }

    private String studentStringFixture() {
        Student student = new Student("Petr", "Pupkin", 2011, 0.345);
        return String.format("%s %s %s %s",
                student.firstName(), student.lastName(), student.getGraduatedYear(), student.getScore());
    }

}