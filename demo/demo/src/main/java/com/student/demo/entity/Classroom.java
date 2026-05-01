package com.student.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String teacher;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Classroom () {}

    public Classroom (Long id, String name, String teacher, List<Student> students) {

        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;

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

    public List<Student> getStudents () {

        return students;

    }

    public void setName (String name) {

        this.name = name;

    }

    public void setTeacher (String teacher) {

        this.teacher = teacher;

    }

    public void setStudents (List<Student> students) {

        this.students = students;

    }

}
