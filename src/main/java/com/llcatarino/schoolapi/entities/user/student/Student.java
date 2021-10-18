package com.llcatarino.schoolapi.entities.user.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llcatarino.schoolapi.entities.course.Course;
import com.llcatarino.schoolapi.entities.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends User {

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email, LocalDate dob, Set<Course> courses) {
        super(firstName, lastName, email, dob);
        this.courses = courses;
    }

    public Student(String firstName, String lastName, String email, LocalDate dob) {
        super(firstName, lastName, email, dob);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                "courses=" + courses +
                '}';
    }
}
