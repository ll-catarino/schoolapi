package com.llcatarino.schoolapi.entities.course;

public class CourseDTO {

    private String name;
    private Long teacherId;

    public CourseDTO(String name, Long teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
