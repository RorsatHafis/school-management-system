package com.student.demo.dto;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String grade;
    private String classroomName;

    public StudentResponseDTO () {}

    public StudentResponseDTO (Long id, String name, int age, String grade, String classroomName) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.classroomName = classroomName;

    }

    public Long getId () {

        return id;

    }

    public String getName () {

        return name;

    }

    public int getAge () {

        return age;

    }

    public String getGrade () {

        return grade;

    }

    public String getClassroomName () {

        return classroomName;

    }
    
}
