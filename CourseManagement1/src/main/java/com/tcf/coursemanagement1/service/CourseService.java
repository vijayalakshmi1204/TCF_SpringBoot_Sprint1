package com.tcf.coursemanagement1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcf.coursemanagement1.entity.Course;
import com.tcf.coursemanagement1.entity.Subject;

import com.tcf.coursemanagement1.repository.CourseRepository;
import com.tcf.coursemanagement1.repository.SubjectRepository;

import com.tcf.coursemanagement1.exception.CourseNotFoundException;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public CourseService(
            CourseRepository courseRepository,
            SubjectRepository subjectRepository) {

        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    // 1. Add Course with Subjects
    public Course addCourse(Course course) {

        if (courseRepository.existsByCourseCode(
                course.getCourseCode())) {

            throw new IllegalArgumentException(
                    "Course code already exists");
        }

        return courseRepository.save(course);
    }

    // 2. Get All Courses
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {

        return courseRepository.findAll();
    }

    // 3. Get Course By ID
    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() ->
                    new CourseNotFoundException(
                        "Course not found with ID: " + id));
    }

    // 4. Get Courses By Department ID
    @Transactional(readOnly = true)
    public List<Course> getCoursesByDepartment(Long departmentId) {

        return courseRepository.findByDepartmentId(departmentId);
    }

    // 5. Update Course
    public Course updateCourse(Long id, Course updatedCourse) {

        Course course = getCourseById(id);

        if (!course.getCourseCode().equals(
                updatedCourse.getCourseCode())
                && courseRepository.existsByCourseCode(
                        updatedCourse.getCourseCode())) {

            throw new IllegalArgumentException(
                    "Course code already exists");
        }

        course.setCourseCode(updatedCourse.getCourseCode());
        course.setCourseName(updatedCourse.getCourseName());
        course.setDepartmentId(updatedCourse.getDepartmentId());
        course.setDuration(updatedCourse.getDuration());
        course.setDegreeType(updatedCourse.getDegreeType());

        return courseRepository.save(course);
    }

    // 6. Delete Course
    public void deleteCourse(Long id) {

        Course course = getCourseById(id);

        courseRepository.delete(course);
    }

    // 7. Add Subject To Existing Course
    public Subject addSubject(Long courseId, Subject subject) {

        Course course = getCourseById(courseId);

        if (subjectRepository.existsBySubjectCode(
                subject.getSubjectCode())) {

            throw new IllegalArgumentException(
                    "Subject code already exists");
        }

        course.addSubject(subject);

        courseRepository.save(course);

        return subject;
    }

    // 8. Get Subjects By Course ID
    @Transactional(readOnly = true)
    public List<Subject> getSubjectsByCourse(Long courseId) {

        getCourseById(courseId);

        return subjectRepository.findByCourse_CourseId(courseId);
    }

    // 9. Get Subjects By Semester
    @Transactional(readOnly = true)
    public List<Subject> getSubjectsBySemester(
            Long courseId, Integer semester) {

        getCourseById(courseId);

        return subjectRepository
                .findByCourse_CourseIdAndSemester(
                        courseId, semester);
    }
}