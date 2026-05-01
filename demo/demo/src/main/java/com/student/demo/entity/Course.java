package com.student.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course () {}

    public Course (Long id, String name, List<Student> students) {

        this.id = id;
        this.name = name;
        this.students = students;

    }

    public Long getId () {

        return id;

    }

    public String getName () {

        return name;

    }

    public List<Student> getStudents () {

        return students;

    }

    public void setName (String name) {

        this.name = name;

    }

    public void setStudents (List<Student> students) {

        this.students = students;

    }
    
}
