package com.student.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class ClassroomRequestDTO {
    
    @NotBlank(message = "Name must not be empty!")
    private String name;

    @NotBlank(message = "Teacher is required!")
    private String teacher;

    

    public String getName () {

        return name;

    }

    public String getTeacher () {

        return teacher;

    }

    public void setName (String name) {

        this.name = name;

    }

    public void setTeacher (String teacher) {

        this.teacher = teacher;

    }


}
