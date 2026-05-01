package com.student.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.demo.dto.ClassroomRequestDTO;
import com.student.demo.dto.StudentResponseDTO;
import com.student.demo.entity.Classroom;
import com.student.demo.entity.Student;
import com.student.demo.exception.StudentNotFoundException;
import com.student.demo.repository.ClassroomRepository;
import com.student.demo.repository.StudentRepository;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public ClassroomService (ClassroomRepository classroomRepository, StudentRepository studentRepository) {

        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;

    }

    public void createClassroom (ClassroomRequestDTO dto) {

        Classroom classroom = new Classroom();

        classroom.setName(dto.getName());
        classroom.setTeacher(dto.getTeacher());

        classroomRepository.save(classroom);

    }

    public String assignStudentToClassroom (Long studentId, Long classroomId) {

        Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found: " + studentId
                    ));

        Classroom classroom = classroomRepository.findById(classroomId)
                    .orElseThrow(() -> new RuntimeException(
                        "Classroom not found: " + classroomId
                    ));

        student.setClassroom(classroom);
        studentRepository.save(student);
        return student.getName() + " assigned to " + classroom.getName();

    }

    public List<StudentResponseDTO> getStudentsInClassroom (Long classroomId) {

        Classroom classroom = classroomRepository.findById(classroomId)
                    .orElseThrow(() -> new RuntimeException(
                        "Classroom not found: " + classroomId
                    ));

        return classroom.getStudents()
                    .stream()
                    .map(s -> new StudentResponseDTO(
                        s.getId(), s.getName(), s.getAge(), s.getGrade(), s.getClassroom().getName()
                    ))
                    .toList();

    }
    
}
