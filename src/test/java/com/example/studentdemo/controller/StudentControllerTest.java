package com.example.studentdemo.controller;

import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.mapper.StudentMapper;
import com.example.studentdemo.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;


    @Test
    void test_saveStudent_success(){
        StudentDTO studentDTO  = StudentDTO.builder().name("Anagha").location("Pune").mobileNo("7612345678").email("Anagha@gmail.com").build();

        Mockito.when(studentService.save(studentDTO)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response  = studentController.saveStudent(studentDTO);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals(studentDTO.getMobileNo(),response.getBody().getMobileNo());
    }

    @Test
    void test_getStudent_success(){
        StudentDTO studentDTO  = StudentDTO.builder().studentId(1L).name("Anagha").location("Pune").mobileNo("7612345678").email("Anagha@gmail.com").build();

        Mockito.when(studentService.getStudentById(1L)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response  = studentController.getStudent(1L);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(studentDTO.getMobileNo(),response.getBody().getMobileNo());
    }


}
