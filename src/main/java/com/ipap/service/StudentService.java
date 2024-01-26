package com.ipap.service;

import com.ipap.dao.Student;
import com.ipap.dto.StudentDto;
import com.ipap.repository.StudentRepository;
import com.ipap.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> findAllStudents() {
        log.info("Find all students");
        return studentRepository.findAll().stream().map(Mapper::toDto).toList();
    }

    public Optional<StudentDto> findStudentByName(String lastname) {
        log.info("Find student by lastname: {}", lastname);
        return studentRepository.findStudentByLastname(lastname).map(Mapper::toDto);
    }

    public StudentDto saveStudent(StudentDto studentDto) {
        Student savedStudent = studentRepository.save(Mapper.toStudent(studentDto));
        log.info("Student successfully saved: {}", savedStudent);
        return Mapper.toDto(savedStudent);
    }
}
