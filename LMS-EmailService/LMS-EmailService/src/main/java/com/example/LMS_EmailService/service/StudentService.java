package com.example.LMS_EmailService.service;

import com.example.LMS_EmailService.dto.Student;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    public Student getStudent(){
        return new Student(102, "Ritesh");
    }
}
