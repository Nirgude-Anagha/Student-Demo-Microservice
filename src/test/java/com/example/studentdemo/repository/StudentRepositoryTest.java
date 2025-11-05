package com.example.studentdemo.repository;

import com.example.studentdemo.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testFindByMobileNo_Success() {
        Student student = new Student();
        student.setMobileNo("7350290333");
        student.setName("Anagha");
        student.setEmail("anagha@gmail.com");
        student.setLocation("Pune");
        studentRepository.save(student);

        Optional<Student> studentOptional = studentRepository.findByMobileNo("7350290333");
        Assertions.assertTrue(studentOptional.isPresent());
        Assertions.assertEquals(studentOptional.get(), student);
    }

    @Test
    void testFindByMobileNo_notFound() {
        Student student = new Student();
        student.setMobileNo("7350290333");
        student.setName("Anagha");
        student.setEmail("anagha@gmail.com");
        student.setLocation("Pune");
        studentRepository.save(student);

        Optional<Student> studentOptional = studentRepository.findByMobileNo("7350290332");
        Assertions.assertTrue(studentOptional.isEmpty());
    }


}
