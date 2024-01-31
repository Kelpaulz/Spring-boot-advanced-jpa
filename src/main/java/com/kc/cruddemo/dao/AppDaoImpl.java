package com.kc.cruddemo.dao;

import com.kc.cruddemo.entity.Course;
import com.kc.cruddemo.entity.Instructor;
import com.kc.cruddemo.entity.InstructorDetail;
import com.kc.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{
    //define field for entity manager
    private EntityManager entityManager;

    //Inject entity manager using constructor injection
    @Autowired
    public AppDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional //To make modification to the database
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }
    @Override
    public Instructor findInstructorById(int theId){
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId){
        return entityManager.find(InstructorDetail.class, theId);
    }
    @Transactional
    @Override
    public void deleteInstructorDetailById(int theId) {
        //retrieve the instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        //remove the instructor
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //Create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        //Execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //Create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetail "
                + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }
    @Override
    @Transactional
    public void update(Instructor tempInstrutor){
        entityManager.merge(tempInstrutor); //merge method is used to update an existing entity
    }
    @Override
    @Transactional
    public void update(Course tempCourse){
        entityManager.merge(tempCourse); //merge method is used to update an existing entity//
        }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId){
        //find/retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //Get the Courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for instructor such that if the instructor is referenced by
        //another course, there won't be an error message since the association has been removed
        for (Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }
        entityManager.remove(tempInstructor);
    }
    @Override
    @Transactional
    public void deleteCourseById(int theId){
        //find/retrieve the Courses
        Course tempCourse = entityManager.find(Course.class, theId);

        //Delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //Create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);
        //Set Parameter
        query.setParameter("data", theId);
        //Execute query
        Course course = query.getSingleResult();
        return course;
    }
    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        //Create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);
        //Set Parameter
        query.setParameter("data", theId);
        //Execute query
        Course course = query.getSingleResult();
        return course;
    }
    @Override
    public Student findStudentsAndCourseByStudentId(int theId) {
        //Create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);
        //Set Parameter
        query.setParameter("data", theId);
        //Execute query
        Student student = query.getSingleResult();
        return student;
    }
    @Override
    @Transactional
    public void update(Student tempStudent){
        entityManager.merge(tempStudent); //merge method is used to update an existing entity
    }
    @Override
    @Transactional
    public void deleteStudentById(int theId){
        //retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        //Delete the student
        entityManager.remove(tempStudent);
    }
}
