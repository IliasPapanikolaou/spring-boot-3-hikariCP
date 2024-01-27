package com.ipap.controller;

import com.ipap.dto.StudentDto;
import com.ipap.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudentByLastname() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<StudentDto> getStudentByLastname(@PathVariable String lastname) {
        return ResponseEntity.of(studentService.findStudentByName(lastname));
    }

    //@PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.saveStudent(studentDto));
    }

    @PostMapping
    public ResponseEntity<String> saveStudentViaKafka(@RequestBody StudentDto studentDto) {
        studentService.saveStudentViaKafka(studentDto);
        return ResponseEntity.ok("Message sent");
    }
}
