package com.llcatarino.schoolapi.entities.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(path = "{courseId}")
    public Course getCourse(@PathVariable("courseId") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public void registerNewCourse(@RequestBody CourseDTO courseDTO) {
        courseService.addNewCourse(courseDTO);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody CourseDTO courseDTO
    ) {
        courseService.updateCourse(courseId, courseDTO);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "{courseId}/enrol/{studentId}")
    public void enrolStudent(
            @PathVariable("courseId") Long courseId,
            @PathVariable("studentId") Long studentId
    ) {
        courseService.enrolStudentToCourse(courseId, studentId);
    }

    @DeleteMapping(path = "{courseId}/leave/{studentId}")
    public void deleteStudent(
            @PathVariable("courseId") Long courseId,
            @PathVariable("studentId") Long studentId
    ) {
        courseService.deleteStudentFromCourse(courseId, studentId);
    }
}
