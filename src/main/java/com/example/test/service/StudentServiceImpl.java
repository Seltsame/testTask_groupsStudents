package com.example.test.service;

import com.example.test.dto.StudentResponseDto;
import com.example.test.dto.request.StudentDto;
import com.example.test.model.Student;
import com.example.test.repository.GroupRepository;
import com.example.test.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public void addStudent(StudentDto studentDto) {
        studentRepository.save(getStudentFromStudentDto(studentDto));
    }

    @Override
    public List<StudentResponseDto> getStudentByGroupId(Long id) {
        return studentRepository.findAllByGroupId(id).stream()
                .map(this::getStudentResponseDtoFromStudent)
                .collect(Collectors.toList());

    }


    private List<StudentResponseDto> getStudentResponseDtoListFromStudentList(List<Student> allByGroupName) {
        return allByGroupName.stream()
                .map(this::getStudentResponseDtoFromStudent)
                .collect(Collectors.toList());
    }


    private Student getStudentFromStudentDto(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getStudentName());
        student.setGroup(groupRepository.findById(studentDto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("Группа не найдена: " + studentDto.getGroupId())));
        student.setCreationDate(LocalDate.now());
        return student;
    }

    private StudentResponseDto getStudentResponseDtoFromStudent(Student student) {
        return StudentResponseDto.builder()
                .name(student.getName())
                .creationDate(student.getCreationDate())
                .build();
    }
}
