package com.llcatarino.schoolapi.entities.course;

import com.llcatarino.schoolapi.entities.user.student.StudentService;
import com.llcatarino.schoolapi.entities.user.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherService teacherService, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    public List<Course> getAllCourses() {
        return  courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);

        if (!exists) {
            throw new IllegalStateException("course with id " + courseId + " does not exist");
        }

        return courseRepository.findById(courseId).get();
    }

    public void addNewCourse(CourseDTO courseDTO) {
        Optional<Course> courseOptional = courseRepository.findCourseByName(courseDTO.getName());

        if (courseOptional.isPresent()) {
            throw new IllegalStateException("course with name already exists");
        }

        Course course = new Course();

        course.setName(courseDTO.getName());
        course.setTeacher(teacherService.getTeacherById(courseDTO.getTeacherId()));

        courseRepository.save(course);
    }

    @Transactional
    public void updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (courseDTO.getName() != null && courseDTO.getName().length() > 0) {
            course.setName(courseDTO.getName());
        }

        if (courseDTO.getTeacherId() != null) {
            course.setTeacher(teacherService.getTeacherById(courseDTO.getTeacherId()));
        }
    }

    public void deleteCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);

        if (!exists) {
            throw new IllegalStateException("course with id " + courseId + " does not exist");
        }

        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void enrolStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        course.enrolStudent(studentService.getStudentById(studentId));
    }

    @Transactional
    public void deleteStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        course.deleteStudent(studentService.getStudentById(studentId));
    }
}
