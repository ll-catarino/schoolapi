package com.llcatarino.schoolapi.entities.user.teacher;

import com.llcatarino.schoolapi.entities.user.User;
import com.llcatarino.schoolapi.entities.user.UserRepository;
import com.llcatarino.schoolapi.entities.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final UserService userService;

    @Autowired
    public TeacherService(UserRepository userRepository, TeacherRepository teacherRepository, UserService userService) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.userService = userService;
    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);

        if (!exists) {
            throw new IllegalStateException("teacher with id " + teacherId + " does not exist");
        }

        return teacherRepository.findById(teacherId).get();
    }

    public void addNewTeacher(Teacher teacher) {
        Optional<User> userOptional = userRepository.findUserByEmail(teacher.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("user with email already exists");
        }

        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long teacherId, Teacher newTeacher) {
       userService.updateUser(teacherId, newTeacher);
    }

    public void deleteTeacher(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);

        if (!exists) {
            throw new IllegalStateException("teacher with id " + teacherId + " does not exist");
        }

        teacherRepository.deleteById(teacherId);
    }

}
