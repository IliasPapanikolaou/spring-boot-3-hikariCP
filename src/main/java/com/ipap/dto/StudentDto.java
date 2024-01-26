package com.ipap.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ipap.dao.Grade;

import java.util.Set;

public class StudentDto {
        private String firstname;
        private String lastname;
        private String grade;
        private Integer age;
        //@JsonManagedReference
        //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
        private Set<Grade> grades;

        public StudentDto() {
        }

        public StudentDto(String firstname, String lastname, String grade, Integer age, Set<Grade> grades) {
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

        public void setGrades(Set<Grade> grades) {
                this.grades = grades;
        }

        @Override
        public String toString() {
                return "StudentDto{" +
                        "firstname='" + firstname + '\'' +
                        ", lastname='" + lastname + '\'' +
                        ", grade='" + grade + '\'' +
                        ", age=" + age +
                        ", grades=" + grades +
                        '}';
        }
}
