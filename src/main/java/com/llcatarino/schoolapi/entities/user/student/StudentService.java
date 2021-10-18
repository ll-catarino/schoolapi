package com.llcatarino.schoolapi.entities.user.student;

import com.llcatarino.schoolapi.entities.user.User;
import com.llcatarino.schoolapi.entities.user.UserRepository;
import com.llcatarino.schoolapi.entities.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final UserService userService;

    @Autowired
    public StudentService(UserRepository userRepository, StudentRepository studentRepository, UserService userService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.userService = userService;
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }

        return studentRepository.findById(studentId).get();
    }

    public void addNewStudent(Student student) {
        Optional<User> userOptional = userRepository.findUserByEmail(student.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("user with email already exists");
        }

        studentRepository.save(student);

    }

    public void updateStudent(Long studentId, Student newStudent) {
        userService.updateUser(studentId, newStudent);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);
    }
}
