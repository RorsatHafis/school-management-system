package com.student.demo.dto;

import java.util.List;

import com.student.demo.entity.Student;

public class ClassroomResponseDTO {

    private Long id;
    private String name;
    private String teacher;
    private List<Student> studentNames;

    public ClassroomResponseDTO () {}

    public ClassroomResponseDTO (Long id, String name, String teacher, List<Student> studentNames) {

        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.studentNames = studentNames;

    }
    
    public Long getId () {

        return id;

    }

    public String getName () {

        return name;

    }

    public String getTeacher () {

        return teacher;

    }

    public List<Student> getStudentNames () {

        return studentNames;

    }

}
