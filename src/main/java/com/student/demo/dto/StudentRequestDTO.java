package com.student.demo.dto;

import jakarta.validation.constraints.*;

public class StudentRequestDTO {

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @Min(value = 1, message = "Age must be at least 1!")
    @Max(value = 100, message = "Age cannot exceed 100!")
    private int age;

    @NotBlank(message = "Grade cannot be empty!")
    private String grade;

    public String getName () {

        return name;

    }

    public int getAge () {

        return age;

    }

    public String getGrade () {

        return grade;

    }

    public void setName (String name) {

        this.name = name;

    }

    public void setAge (int age) {

        this.age = age;

    }

    public void setGrade (String grade) {

        this.grade = grade;

    }

}
