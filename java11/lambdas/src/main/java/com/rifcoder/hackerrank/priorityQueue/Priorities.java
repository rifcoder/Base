package com.rifcoder.hackerrank.priorityQueue;

import java.util.*;

public class Priorities {
    private final PriorityQueue<Student> queue = new PriorityQueue<>();

    public List<Student> getStudents(List<String> events) {
        for (String e : events) {
            String event = e.trim();
            if (event.equals("SERVED")) {
                queue.poll();
            } else {
                Student student = parseStudentData(event);
                queue.offer(student);
            }
        }

        return constructResultedList(queue);
    }

    private Student parseStudentData(String event) {
        String[] studentParts = event.split("\\s+");
        ensureFormat(event, studentParts);

        int id = Integer.parseInt(studentParts[3]);
        String name = studentParts[1];
        double cgpa = Double.parseDouble(studentParts[2]);

        return new Student(id, name, cgpa);
    }

    private void ensureFormat(String event, String[] studentParts) {
        if (studentParts.length != 4 || !studentParts[0].equals("ENTER")) {
            throw new IllegalArgumentException("Illegal student entry format" + event);
        }
    }

    private List<Student> constructResultedList(PriorityQueue<Student> queue) {
        ArrayList<Student> result = new ArrayList<>(queue.size());
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
}