package com.example.studentdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = "email"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long studentId;

   @Column(nullable = false)
   private String name;
   private String location;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false, length = 10)
   private String mobileNo;


}
