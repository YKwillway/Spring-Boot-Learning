package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    // @Value: application.propertiesの定義値とバインディングしている
    @Value("${countries}")
    private List<String> countries;
    @Value("${languages}")
    private List<String> languages;
    @Value("${operatingSystems}")
    private List<String> operatingSystems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {
        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        theModel.addAttribute("countries", countries);
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("operatingSystems", operatingSystems);
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent) {
        System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        return "student-confirmation";
    }
}
