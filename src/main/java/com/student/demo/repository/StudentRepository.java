package com.student.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.age BETWEEN :minAge AND :maxAge")
    List<Student> findByAgeBetween (
        @Param("minAge") int minAge,
        @Param("maxAge") int maxAge
    );

    @Query("SELECT s.grade, COUNT(s) FROM Student s GROUP BY s.grade")
    List<Object[]> countByGrade();

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByName (@Param("keyword") String keyword);

    Page<Student> findByGrade (String grade, Pageable pageable);
    
}
