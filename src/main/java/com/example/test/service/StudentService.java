package com.example.test.service;

import com.example.test.dto.StudentResponseDto;
import com.example.test.dto.request.StudentDto;

import java.util.List;

public interface StudentService {
    void addStudent(StudentDto studentDto);

    List<StudentResponseDto> getStudentByGroupId(Long id);
}
