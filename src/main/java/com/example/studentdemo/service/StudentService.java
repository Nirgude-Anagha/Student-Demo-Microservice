package com.example.studentdemo.service;

import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.exception.InvalidStudentDataException;
import com.example.studentdemo.exception.StudentAlreadyExistsException;
import com.example.studentdemo.exception.StudentNotFoundException;
import com.example.studentdemo.mapper.StudentMapper;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO save(StudentDTO studentDTO){
        if(studentDTO==null){
            throw new InvalidStudentDataException("Invalid Student details");
        }
        studentRepository.findByMobileNo(studentDTO.getMobileNo()).ifPresent(s->{
            throw new StudentAlreadyExistsException("Student with mobileNo : " + studentDTO.getMobileNo() + " already exists");
        });

        Student student = studentRepository.save(StudentMapper.toEntity(studentDTO));
        return StudentMapper.toDTO(student);

    }

    public StudentDTO getStudentById(Long id){
        if(id==null){
            throw new InvalidStudentDataException("Invalid Student Id");
        }
        Student student = studentRepository.findById(id).orElseThrow(()->{
            throw new StudentNotFoundException("Student with " + id + " does not exist.");
        });

        return StudentMapper.toDTO(student);

    }

}
