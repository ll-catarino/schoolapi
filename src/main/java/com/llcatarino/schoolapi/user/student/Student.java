package com.llcatarino.schoolapi.user.student;

import com.llcatarino.schoolapi.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student extends User {

    public Student() {
    }

    public Student(String firstName, String lastName, String email, LocalDate dob) {
        super(firstName, lastName, email, dob);
    }
}
