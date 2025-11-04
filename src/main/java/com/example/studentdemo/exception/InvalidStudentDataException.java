package com.example.studentdemo.exception;

public class InvalidStudentDataException extends RuntimeException {

    public InvalidStudentDataException(String message){
        super(message);
    }
}
