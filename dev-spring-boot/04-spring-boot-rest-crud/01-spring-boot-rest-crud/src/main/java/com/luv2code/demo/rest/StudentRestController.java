package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        var students = new ArrayList<Student>();
        students.add(new Student("Yuki", "Murakoshi"));
        students.add(new Student("Mamoru", "Umemoto"));
        students.add(new Student("Taku", "Oyama"));
        this.theStudents = students;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("studentId doesn't exist - " + studentId);
        }
        return theStudents.get(studentId);
    }
}
