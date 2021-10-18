package com.llcatarino.schoolapi.entities.user.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llcatarino.schoolapi.entities.course.Course;
import com.llcatarino.schoolapi.entities.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends User {

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String email, LocalDate dob) {
        super(firstName, lastName, email, dob);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
