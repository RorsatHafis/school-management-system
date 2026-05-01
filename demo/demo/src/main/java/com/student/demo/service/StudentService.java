package com.student.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.student.demo.dto.GradeCountDTO;
import com.student.demo.dto.StudentRequestDTO;
import com.student.demo.dto.StudentResponseDTO;
import com.student.demo.entity.Student;
import com.student.demo.exception.InvalidSearchException;
import com.student.demo.exception.StudentNotFoundException;
import com.student.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository; 

    public StudentService (StudentRepository repository) {

        this.repository = repository;

    }

    public StudentResponseDTO toResponseDTO (Student student) {

        return new StudentResponseDTO(
            student.getId(),
            student.getName(),
            student.getAge(),
            student.getGrade(),
            student.getClassroom() != null ? student.getClassroom().getName() : null
        );

    }

    public Student addStudent (StudentRequestDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGrade(dto.getGrade());

        return repository.save(student);

    }

    public Page<StudentResponseDTO> getAllStudents (Pageable pageable) {

        return repository.findAll(pageable)
            .map(this::toResponseDTO);

    }

    public StudentResponseDTO getStudentById (Long id) {

        Student student = repository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id: " + id + " not found!"
                    ));

        return toResponseDTO(student);

    }

    public List<StudentResponseDTO> getStudentByName (String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {

            throw new InvalidSearchException("Search keyword must not be empty!");
            
        }

        List<Student> students = repository.searchByName(keyword.trim());

        if (students.isEmpty()) {

            throw new StudentNotFoundException("No student found with keyword: " + keyword);

        }

        return students.stream()
                .map(this::toResponseDTO)
                .toList();

    }

    public List<StudentResponseDTO> getStudentByAgeRange (int minAge, int maxAge) {

        if (minAge < 0 || maxAge < 0) {

            throw new InvalidSearchException("Age cannot be negative!");

        }

        if (minAge > maxAge) {

            throw new InvalidSearchException("minAge cannot be greater than maxAge!");

        }

        List<Student> students = repository.findByAgeBetween(minAge, maxAge);

        if (students.isEmpty()) {

            throw new StudentNotFoundException(
                "No students found between ages " + minAge + " and " + maxAge
            );

        }

        return students.stream()
                .map(this::toResponseDTO)
                .toList();

    }

    public List<GradeCountDTO> getStudentCountByGrade () {

        List<Object[]> results = repository.countByGrade();

        if (results.isEmpty()) {

            throw new StudentNotFoundException("No grade data found!");

        }

        return results.stream()
                    .map(obj -> new GradeCountDTO(
                        (String) obj[0],
                        (long) obj[1]
                    ))
                    .toList();

    }

    public Page<StudentResponseDTO> getStudentByGrade (String grade, Pageable pageable) {

        return repository.findByGrade(grade, pageable)
            .map(this::toResponseDTO);
                    
    }

    public Student updateStudent (Long id, StudentRequestDTO dto) {

        return repository.findById(id)
                        .map(s -> {
                            s.setName(dto.getName());
                            s.setAge(dto.getAge());
                            s.setGrade(dto.getGrade());
                            return repository.save(s);
                        })
                        .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id: " + id + " not found!"
                    ));

    }

    public void deleteStudent (Long id) {

        if(!repository.existsById(id)) {

            throw new StudentNotFoundException("Student with id: " + id + " not found!");

        }

        repository.deleteById(id);

    }

}
