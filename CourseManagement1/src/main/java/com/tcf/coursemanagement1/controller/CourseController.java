package com.tcf.coursemanagement1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.tcf.coursemanagement1.entity.Course;
import com.tcf.coursemanagement1.entity.Subject;

import com.tcf.coursemanagement1.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 1. Add Course
    @PostMapping
    public ResponseEntity<Course> addCourse(
            @Valid @RequestBody Course course) {

        return ResponseEntity.ok(
                courseService.addCourse(course));
    }

    // 2. Get All Courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(
                courseService.getAllCourses());
    }

    // 3. Get Course By ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id));
    }

    // 4. Get Courses By Department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Course>> getCoursesByDepartment(
            @PathVariable Long departmentId) {

        return ResponseEntity.ok(
                courseService.getCoursesByDepartment(departmentId));
    }

    // 5. Update Course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {

        return ResponseEntity.ok(
                courseService.updateCourse(id, course));
    }

    // 6. Delete Course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok(
                "Course deleted successfully");
    }

    // 7. Add Subject To Course
    @PostMapping("/{courseId}/subjects")
    public ResponseEntity<Subject> addSubject(
            @PathVariable Long courseId,
            @Valid @RequestBody Subject subject) {

        return ResponseEntity.ok(
                courseService.addSubject(courseId, subject));
    }

    // 8. Get All Subjects By Course
    @GetMapping("/{courseId}/subjects")
    public ResponseEntity<List<Subject>> getSubjects(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                courseService.getSubjectsByCourse(courseId));
    }

    // 9. Get Subjects By Semester
    @GetMapping("/{courseId}/subjects/semester/{semester}")
    public ResponseEntity<List<Subject>> getSubjectsBySemester(
            @PathVariable Long courseId,
            @PathVariable Integer semester) {

        return ResponseEntity.ok(
                courseService.getSubjectsBySemester(
                        courseId, semester));
    }
}