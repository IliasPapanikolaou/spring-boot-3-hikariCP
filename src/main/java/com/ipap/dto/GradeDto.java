package com.ipap.dto;

import com.ipap.dao.Student;

public class GradeDto {

    private Integer semester;
    private String course;
    private Float grade;
    private Student student;

    public GradeDto() {
    }

    public GradeDto(Integer semester, String course, Float grade, Student student) {
        this.semester = semester;
        this.course = course;
        this.grade = grade;
        this.student = student;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "GradeDto{" +
                "semester=" + semester +
                ", course='" + course + '\'' +
                ", grade=" + grade +
                ", student=" + student +
                '}';
    }
}
