package com.student.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private int age;
    private String grade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student () {}

    public Student (Long id, String name, int age, String grade, Classroom classroom, List<Course> courses) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.classroom = classroom;
        this.courses = courses;

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

    public Classroom getClassroom () {

        return classroom;

    }

    public List<Course> getCourses () {

        return courses;

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

    public void setClassroom (Classroom classroom) {

        this.classroom = classroom;

    }

    public void setCourses (List<Course> courses) {

        this.courses =courses;

    }

}
