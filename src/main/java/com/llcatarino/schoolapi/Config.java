package com.llcatarino.schoolapi;

import com.llcatarino.schoolapi.entities.course.Course;
import com.llcatarino.schoolapi.entities.course.CourseRepository;
import com.llcatarino.schoolapi.entities.user.student.Student;
import com.llcatarino.schoolapi.entities.user.student.StudentRepository;
import com.llcatarino.schoolapi.entities.user.teacher.Teacher;
import com.llcatarino.schoolapi.entities.user.teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        return args -> {
            Student john = new Student(
                    "Paul",
                    "McCartney",
                    "paul@beatles.com",
                    LocalDate.of(1942, 6, 18)
            );

            Student bob = new Student(
                    "George",
                    "Harrison",
                    "george@mail.com",
                    LocalDate.of(1943, 2, 25)
            );

            studentRepository.saveAll(List.of(john, bob));

            Teacher lennon = new Teacher(
                    "John",
                    "Lennon",
                    "john@beatles.com",
                    LocalDate.of(1940, 10, 9)
            );

            Teacher ringo = new Teacher(
                    "Ringo",
                    "Starr",
                    "ringo@beatles.com",
                    LocalDate.of(1940, 7, 7)
            );

            teacherRepository.saveAll(List.of(lennon, ringo));

            Course algebra = new Course(
                    "Drumming",
                    ringo,
                    Set.of(john, bob)
            );

            courseRepository.save(algebra);
        };
    }
}
