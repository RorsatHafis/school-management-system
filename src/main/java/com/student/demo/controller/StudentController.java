package com.student.demo.controller;

import com.student.demo.dto.GradeCountDTO;
import com.student.demo.dto.PageResponseDTO;
import com.student.demo.dto.StudentRequestDTO;
import com.student.demo.dto.StudentResponseDTO;
import com.student.demo.service.StudentService;

import jakarta.validation.Valid;
import main.java.com.student.demo.common.AppConstants;
import main.java.com.student.demo.dto.ApiResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController (StudentService service) {

        this.service = service;

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PageResponseDTO<StudentResponseDTO>>> getAllStudent (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<StudentResponseDTO> result = service.getAllStudents(pageable);
        
        return ResponseEntity.ok(
            ApiResponse.success("Students retrieved", new PageResponseDTO<>(result))
        );

    }

    @PostMapping
    public ResponseEntity<String> addStudent (@Valid @RequestBody StudentRequestDTO dto) {

        service.addStudent(dto);
        return ResponseEntity.status(201).body(AppConstants.STUDENT_ADDED);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getById (@PathVariable Long id) {

        return ResponseEntity.ok(
            ApiResponse.success("Student retrieved", service.getStudentById(id))
        );

    }

    @GetMapping("/search/name")
    public ResponseEntity<List<StudentResponseDTO>> getByName (@RequestParam String keyword) {

        List<StudentResponseDTO> result = service.getStudentByName(keyword);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/age-range")
    public ResponseEntity<List<StudentResponseDTO>> getByAgeRange (
        @RequestParam int minAge,
        @RequestParam int maxAge
    ) {

        List<StudentResponseDTO> result = service.getStudentByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/grade")
    public ResponseEntity<PageResponseDTO<StudentResponseDTO>> searchByGrade (
        @RequestParam String grade,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<StudentResponseDTO> result = service.getStudentByGrade(grade, pageable);

        return ResponseEntity.ok(new PageResponseDTO<>(result));

    }

    @GetMapping("/grade-count")
    public ResponseEntity<List<GradeCountDTO>> countByGrade () {

        List<GradeCountDTO> result = service.getStudentCountByGrade();
        return ResponseEntity.ok(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent (
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dtoUpdate) {

                service.updateStudent(id, dtoUpdate);
                return ResponseEntity.ok("Student updated successfully!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable Long id) {

        service.deleteStudent(id);
        return ResponseEntity.noContent().build();

    }
    
}
