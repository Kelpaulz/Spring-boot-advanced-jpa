package com.kc.cruddemo.dao;

import com.kc.cruddemo.entity.Course;
import com.kc.cruddemo.entity.Instructor;
import com.kc.cruddemo.entity.InstructorDetail;
import com.kc.cruddemo.entity.Student;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    @Transactional
    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    @Transactional
    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    @Transactional
    void update(Instructor tempInstrutor);

    @Transactional
    void update(Course tempCourse);

    Course findCourseById(int theId);

    @Transactional
    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentsAndCourseByStudentId(int theId);

    @Transactional
    void update(Student tempStudent);

    @Transactional
    void deleteStudentById(int theId);
}