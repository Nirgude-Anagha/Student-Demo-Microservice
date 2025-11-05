package com.example.studentdemo.service;

import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.exception.InvalidStudentDataException;
import com.example.studentdemo.exception.StudentAlreadyExistsException;
import com.example.studentdemo.exception.StudentNotFoundException;
import com.example.studentdemo.mapper.StudentMapper;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        studentDTO = StudentDTO.builder().name("Anagha").email("anagha@gmail.com")
                .mobileNo("7350290332").location("Pune").build();
    }

    @Test
    void createStudent_success(){
        Student s = StudentMapper.toEntity(studentDTO);
        s.setStudentId(1L);
        Mockito.when(studentRepository.findByMobileNo(studentDTO.getMobileNo())).thenReturn(Optional.empty());
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(s);

        StudentDTO studentDTO1 = studentService.save(studentDTO);
        Assertions.assertNotNull(studentDTO1);
        Assertions.assertNotNull(studentDTO1.getStudentId());
        Assertions.assertNotNull(studentDTO1.getMobileNo());

    }

    @Test
    void createStudent_throwsInvalidStudentDataException(){
        Assertions.assertThrows(InvalidStudentDataException.class, () -> studentService.save(null));

    }

    @Test
    void createStudent_throwsStudentAlreadyExistsException(){
        Student s = StudentMapper.toEntity(studentDTO);
        s.setStudentId(1L);
        Mockito.when(studentRepository.findByMobileNo(studentDTO.getMobileNo())).thenReturn(Optional.of(s));

        Assertions.assertThrows(StudentAlreadyExistsException.class, () -> studentService.save(studentDTO));


    }

    @Test
    void getStudentById_success(){
        Student s = StudentMapper.toEntity(studentDTO);
        s.setStudentId(1L);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(s));
        StudentDTO studentDTO1 = studentService.getStudentById(1L);
        Assertions.assertNotNull(studentDTO1);
        Assertions.assertNotNull(studentDTO1.getStudentId());
        Assertions.assertNotNull(studentDTO1.getMobileNo());

    }

    @Test
    void getStudentById_throwsInvalidStudentDataException(){
        Assertions.assertThrows(InvalidStudentDataException.class, () -> studentService.getStudentById(null));

    }

    @Test
    void getStudentById_throwsStudentNotFoundException(){
        Mockito.when(studentRepository.findById((2L))).thenReturn(Optional.empty());
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(2L));

    }


}
