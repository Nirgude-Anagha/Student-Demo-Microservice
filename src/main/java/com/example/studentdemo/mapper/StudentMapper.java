package com.example.studentdemo.mapper;

import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static Student toEntity(StudentDTO studentDTO) {
        return Student.builder().studentId(studentDTO.getStudentId()).name(studentDTO.getName())
                .location(studentDTO.getLocation()).email(studentDTO.getEmail()).mobileNo(studentDTO.getMobileNo()).build();
    }

    public static StudentDTO toDTO(Student student) {
        return StudentDTO.builder().studentId(student.getStudentId()).name(student.getName())
                .location(student.getLocation()).email(student.getEmail()).mobileNo(student.getMobileNo()).build();
    }
}
