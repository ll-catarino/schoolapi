package com.llcatarino.schoolapi.user.teacher;

import com.llcatarino.schoolapi.user.User;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Teacher extends User {

    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String email, LocalDate dob) {
        super(firstName, lastName, email, dob);
    }
}
