package com.tcf.coursemanagement1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tcf.coursemanagement1.entity.Course;

public interface CourseRepository
        extends JpaRepository<Course, Long> {

    @EntityGraph(attributePaths = "subjects")
    List<Course> findAll();

    @Override
    @EntityGraph(attributePaths = "subjects")
    Optional<Course> findById(Long id);

    @EntityGraph(attributePaths = "subjects")
    List<Course> findByDepartmentId(Long departmentId);

    boolean existsByCourseCode(String courseCode);
}