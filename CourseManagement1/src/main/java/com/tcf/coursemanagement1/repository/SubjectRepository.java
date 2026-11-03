package com.tcf.coursemanagement1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcf.coursemanagement1.entity.Subject;

public interface SubjectRepository
        extends JpaRepository<Subject, Long> {

    List<Subject> findByCourse_CourseId(Long courseId);

    List<Subject> findByCourse_CourseIdAndSemester(
            Long courseId, Integer semester);

    boolean existsBySubjectCode(String subjectCode);
}