package com.student.demo.dto;

public class GradeCountDTO {

    private String grade;
    private long count;

    public GradeCountDTO (String grade, long count) {

        this.grade = grade;
        this.count = count;

    }

    public String getGrade () {

        return grade;

    }

    public long getCount () {

        return count;

    }
    
}
