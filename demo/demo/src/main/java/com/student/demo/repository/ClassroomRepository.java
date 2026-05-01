package com.student.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.entity.Classroom;

public interface ClassroomRepository extends JpaRepository <Classroom, Long>{

    
} 