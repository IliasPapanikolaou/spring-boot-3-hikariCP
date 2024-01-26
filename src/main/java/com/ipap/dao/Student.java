package com.ipap.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String grade;
    private Integer age;
    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;

    public Student() {
    }

    public Student(String firstname, String lastname, String grade, Integer age, Set<Grade> grades) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
        this.age = age;
        this.grades = grades;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void addGrades(Grade grade) {
        this.grades.add(grade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", grade='" + grade + '\'' +
                ", age=" + age +
                ", grades=" + grades +
                '}';
    }
}
