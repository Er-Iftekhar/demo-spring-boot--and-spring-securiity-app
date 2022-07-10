package com.app.demospringsecurityappwithamigo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentRestController {

    private final List<Student> STUDENTS = Arrays.asList(
            new Student(2, "James Bond"),
            new Student(3, "Maria Jones"),
            new Student(4, "Anna Smith")
    );


    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable Integer studentId){
        return STUDENTS.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student "+ studentId + " does not exists"));
    }
}
