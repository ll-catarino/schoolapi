package com.llcatarino.schoolapi.entities.course;

import com.llcatarino.schoolapi.entities.user.student.Student;
import com.llcatarino.schoolapi.entities.user.teacher.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course_table")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String name;
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();


    public Course() {
    }

    public Course(String name, Teacher teacher, Set<Student> enrolledStudents) {
        this.name = name;
        this.teacher = teacher;
        this.enrolledStudents = enrolledStudents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void enrolStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void deleteStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            throw new IllegalStateException("student not in course");
        }

        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", enrolledStudents=" + enrolledStudents +
                '}';
    }
}
