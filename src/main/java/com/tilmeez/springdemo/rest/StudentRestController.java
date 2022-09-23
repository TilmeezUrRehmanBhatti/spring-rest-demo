package com.tilmeez.springdemo.rest;

import com.tilmeez.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return list of student

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> theStudent = new ArrayList<>();

        theStudent.add(new Student("Poornima", "Patek"));
        theStudent.add(new Student("Mario", "Rossi"));
        theStudent.add(new Student("Mary", "Smith"));

        return theStudent;
    }

}
