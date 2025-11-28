package com.example.LMS_EmailService.controller;

import com.example.LMS_EmailService.dto.Student;
import com.example.LMS_EmailService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/get")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudent")
     public Student getStudent(){
         return studentService.getStudent();
     }


}
