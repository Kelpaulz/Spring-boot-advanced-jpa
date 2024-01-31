package com.kc.cruddemo;

import com.kc.cruddemo.dao.AppDao;
import com.kc.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner -> {
			//createCourseAndStudents(appDao);
			//findCourseAndStudents(appDao);
			//findStudentAndCourse(appDao);
			//addMoreCoursesForStudents(appDao);
			//deleteTheCourse(appDao);
			deleteStudent(appDao);

		};
	}

	private void deleteStudent(AppDao appDao) {
		int theId = 3;
		System.out.println("Deleting Student Id " + theId);

		appDao.deleteStudentById(theId);

		System.out.println("Done");
	}

	private void addMoreCoursesForStudents(AppDao appDao) {
		//Find the student
		int theId = 2;
		Student tempStudent = appDao.findStudentsAndCourseByStudentId(theId);

		//Create more courses
		Course tempCourse1 = new Course("Physics");
		Course tempCourse2 = new Course("Biology");

		//Add the courses to the Student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		//Save thr Student data
		System.out.println("Savng the Student:" + tempStudent);
		System.out.println("Associated Courses: " + tempStudent.getCourses());
		appDao.update(tempStudent);
	}


	private void findStudentAndCourse(AppDao appDao) {
		int theId = 2;
		Student tempStudent = appDao.findStudentsAndCourseByStudentId(theId);
		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());
	}

	private void findCourseAndStudents(AppDao appDao) {
		int theId = 10;
		Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Student: " + tempCourse.getStudents());
	}

	private void createCourseAndStudents(AppDao appDao) {
		//Create a course
		Course tempCourse = new Course("Maths");
		//create the student
		Student tempStudent1 = new Student("Emeka", "JOhn", "emyjohn@mail.com");
		Student tempStudent2 = new Student("Emekus", "Jajan", "emyjohn@mail.com");
		Student tempStudent3 = new Student("Silver", "Timi", "emyjohn@mail.com");
		// Add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		tempCourse.addStudent(tempStudent3);
		// Save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associated students: " + tempCourse.getStudents());
		appDao.save(tempCourse);
	}

	private void deleteCourseAndReviews(AppDao appDao) {

		int theId = 10;
		System.out.println("Deleting the Course with associated reviews");
		appDao.deleteCourseById(theId);
		//print the reviews
		System.out.println("done");
	}

	private void retrieveCourseAndReviews(AppDao appDao) {
		//Get the course and reviews
		int theId = 10;
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);
		//print the course
		System.out.println(tempCourse);
		//print the reviews
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDao appDao) {
		//Create Course
		Course tempCourse = new Course("Mathematics");

		//Add reviews
		tempCourse.addReview(new Review("Great Course"));
		tempCourse.addReview(new Review("Great Course. I give it a 100 percent score"));
		tempCourse.addReview(new Review("coooooool Course"));

		//save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDao.save(tempCourse);
	}

	private void deleteTheCourse(AppDao appDao) {
		int theId = 10;
		System.out.println("Deleting the Course " + theId);

		//deleting the course
		appDao.deleteCourseById(theId);
	}

	private void deleteTheInstructor(AppDao appDao) {
		int theId = 2;
		System.out.println("Deleting the Instructor " + theId);

		appDao.deleteInstructorById(theId);
		System.out.println("Done");

	}

	private void updateTheCourse(AppDao appDao) {
		int theId = 10;

		//finding the Course using the Id
		System.out.println("FInding the Course" + theId);
		Course tempCourse = appDao.findCourseById(theId);

		//Update the instructor
		System.out.println("Updating the Course Id " + theId);
		tempCourse.setTitle("Emenike is good");

		appDao.update(tempCourse);
		System.out.println("Done ");
	}

	private void updateTheInstructor(AppDao appDao) {
		int theId = 2;

		//finding the instructor using the Id
		System.out.println("FInding the Id" + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		//Update the instructor
		System.out.println("Updating the instructoe Id " + theId);
		tempInstructor.setLastName("Emenike");

		appDao.update(tempInstructor);
		System.out.println("Done ");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int theId =2;

		//find the instructor
		System.out.println("Finding Instructor By Id: " + theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor:" + tempInstructor);

//We don't need these codes anymore because of the Jon fetch which eagerly loads it
//		//find courses for instructor
//
//		System.out.println("finding courses for instructor id:" + theId);
//		List<Course> courses = appDao.findCoursesByInstructorId(theId);
//
//		//Associate the objects
//		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDao appDao) {
		int theId =2;

		//find the instructor
		System.out.println("Finding Courses For Instructor: " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor:" + tempInstructor);

		//find courses for instructor

		System.out.println("finding courses for instructor id:" + theId);
		List<Course> courses = appDao.findCoursesByInstructorId(theId);

		//Associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int theId =2;
		System.out.println("Finding Instructor With Courses: " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("findInstructor:" + tempInstructor);
		System.out.println("The associated details: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDao appDao) {
		//create the instructor
		Instructor tempInstructor = new Instructor("chad", "Darby","kc@love.com");

		//create instructor details
		InstructorDetail	tempInstructorDetail = new InstructorDetail("kc_youtube.com", "backend");

		//Associate the Objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Create tempCourse
		Course tempCourse1 = new Course("Java book1 Ultimate guide");
		Course tempCourse2 = new Course("Java book2 Ultimate guide");
		Course tempCourse3 = new Course("Java book3 Ultimate guide");

		//Add courses to Instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		//Save the Instructor
		// NOTE: This will also save the details object because of CascadeType.All
		//NOTE: This will also save the details object because of CascadeType.PERSIST
		System.out.println("Saving the instructor " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDao.save(tempInstructor);

		System.out.println("Done !!");
	}

	private void findInstructorDetail(AppDao appDao) {
			int theId =1;
			System.out.println("Finding instructorDetail Id: " + theId);
			InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theId);

			System.out.println("tempInstructorDetail:" + tempInstructorDetail);
			System.out.println("The associated details: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDao appDao) {
		int theId =1;
		System.out.println("Deleting instructor Id: " + theId);
		appDao.deleteInstructorById(theId);
		System.out.println("done");
	}

	private void deleteInstructorDetail(AppDao appDao) {
		int theId =1;
		System.out.println("Deleting instructor detail Id: " + theId);
		appDao.deleteInstructorDetailById(theId);
		System.out.println("done");
	}

	private void findInstructor(AppDao appDao) {
		int theId =1;
		System.out.println("Finding instructor Id: " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempinstructor:" + tempInstructor);
		System.out.println("The associated details: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		//create the instructor
		Instructor tempInstructor = new Instructor("chad", "Darby","kc@love.com");

		//create instructor details
		InstructorDetail	tempInstructorDetail = new InstructorDetail("kc_youtube.com", "backend");

		//Associate the Objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Save the Instructor
		// NOTE: This will also save the details object because of CascadeType.All
		//
		System.out.println("Saving the instructor " + tempInstructor);
		appDao.save(tempInstructor);

		System.out.println("Done !!");
	}

}
