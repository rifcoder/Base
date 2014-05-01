package com.rifcoder.common.generator;

import com.rifcoder.common.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonLoaderTest {
    public static final double DELTA = 1e-10;

    @Test
    public void testStudentParser() {
        String studentString = studentStringFixture();
        Student loadedStudent = CommonLoader.loadStudent(studentString);

        assertEquals("First name is wrong!", "Petr", loadedStudent.firstName());
        assertEquals("Last name is wrong!", "Pupkin", loadedStudent.lastName());
        assertEquals("Graduated year is wrong!", 2011, loadedStudent.getGraduatedYear());
        assertEquals("Score is wrong!", 0.345, loadedStudent.getScore(), DELTA);
    }

    private String studentStringFixture() {
        Student student = new Student("Petr", "Pupkin", 2011, 0.345);
        return String.format("%s %s %s %s",
                student.firstName(), student.lastName(), student.getGraduatedYear(), student.getScore());
    }

}