package com.tcf.coursemanagement1.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NotBlank(message = "Course code is required")
    @Column(unique = true, nullable = false)
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Column(nullable = false)
    private String courseName;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 year")
    private Integer duration;

    @NotBlank(message = "Degree type is required")
    private String degreeType;

    @Valid
    @OneToMany(
        mappedBy = "course",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Subject> subjects = new ArrayList<>();

    public Course() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects.clear();

        if (subjects != null) {
            for (Subject subject : subjects) {
                addSubject(subject);
            }
        }
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setCourse(this);
    }
}
