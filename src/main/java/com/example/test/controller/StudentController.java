package com.example.test.controller;

import com.example.test.dto.StudentResponseDto;
import com.example.test.dto.request.StudentDto;
import com.example.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/add")
    public void addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }

    @GetMapping(path = "/getStudentByGroupId")
    public List<StudentResponseDto> getStudentByGroupId(@RequestParam Long id) {
        return studentService.getStudentByGroupId(id);
    }


}
