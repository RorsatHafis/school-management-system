package com.student.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.dto.ClassroomRequestDTO;
import com.student.demo.dto.StudentResponseDTO;
import com.student.demo.service.ClassroomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService service;

    public ClassroomController (ClassroomService service) {

        this.service = service;

    }

    @PostMapping
    public ResponseEntity<String> createClassroom (@Valid @RequestBody ClassroomRequestDTO dto) {

        service.createClassroom(dto);
        return ResponseEntity.status(201).body("Classroom created successfully!");

    }

    @PostMapping("/{classroomI}/assign/{studentId}")
    public ResponseEntity<String> assignStudentToClassroom (@PathVariable Long classroomId, @PathVariable Long studentId) {

        service.assignStudentToClassroom(studentId, classroomId);
        return ResponseEntity.status(201).body("Student assigned to classroom successfully!");

    }

    @GetMapping("/{classroomId}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentInClassroom (@PathVariable Long classroomId) {

        List<StudentResponseDTO> dto = service.getStudentsInClassroom(classroomId);
        return ResponseEntity.ok(dto);



    }
    
}
