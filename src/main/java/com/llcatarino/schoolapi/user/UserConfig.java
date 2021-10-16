package com.llcatarino.schoolapi.user;

import com.llcatarino.schoolapi.user.student.Student;
import com.llcatarino.schoolapi.user.student.StudentRepository;
import com.llcatarino.schoolapi.user.teacher.Teacher;
import com.llcatarino.schoolapi.user.teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        return args -> {
            Student john = new Student(
                    "john",
                    "doe",
                    "john@mail.com",
                    LocalDate.of(2000, 10, 21)
            );

            Student bob = new Student(
                    "bob",
                    "marley",
                    "bob@mail.com",
                    LocalDate.of(2000, 1, 21)
            );

            studentRepository.saveAll(List.of(john, bob));

            Teacher lennon = new Teacher(
                    "john",
                    "lennon",
                    "john@beatles.com",
                    LocalDate.of(1940, 10, 9)
            );

            Teacher ringo = new Teacher(
                    "ringo",
                    "starr",
                    "ringo@beatles.com",
                    LocalDate.of(1940, 7, 7)
            );

            teacherRepository.saveAll(List.of(lennon, ringo));
        };
    }
}
