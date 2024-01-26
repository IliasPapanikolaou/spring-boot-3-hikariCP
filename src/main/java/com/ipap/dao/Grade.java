package com.ipap.dao;

import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer semester;
    private String course;
    private Float grade;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Grade() {
    }

    public Grade(Integer semester, String course, Float grade) {
        this.semester = semester;
        this.course = course;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Grade{" +
                "semester=" + semester +
                ", course='" + course + '\'' +
                ", grade=" + grade +
                '}';
    }
}
