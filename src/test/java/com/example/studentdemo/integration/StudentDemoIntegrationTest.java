package com.example.studentdemo.integration;

import com.example.studentdemo.dto.StudentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDemoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long createdId;

    @Test
    @Order(1)
    void testCreateStudent() throws Exception {
        StudentDTO studentDTO = StudentDTO.builder().name("Anagha").email("Anagha@gmail.com").location("Pune")
                .mobileNo("7350123456").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Anagha"))
                .andDo(result -> {
                    StudentDTO studentDTO1 = objectMapper.readValue(result.getResponse().getContentAsString(), StudentDTO.class);
                    createdId = studentDTO1.getStudentId();
                    Assertions.assertNotNull(createdId);
                });

    }

    @Test
    @Order(2)
    void testGetStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + createdId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Anagha"));
    }
}
