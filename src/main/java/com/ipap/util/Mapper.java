package com.ipap.util;

import com.ipap.dao.Grade;
import com.ipap.dao.Student;
import com.ipap.dto.GradeDto;
import com.ipap.dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static StudentDto toDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public static Student toStudent(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public static GradeDto toDto(Grade grade) {
        return modelMapper.map(grade, GradeDto.class);
    }

    public static Grade toEntity(GradeDto gradeDto) {
        return modelMapper.map(gradeDto, Grade.class);
    }
}
