package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		// Session session = factory.getCurrentSession();
		Session session = factory.getCurrentSession();
		try {

			// create a student object
			System.out.println("Creating a new student object...");
			Student student = new Student("Bill", "Bobo", "bill@luv2code.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student...");
			System.out.println(student);
			session.save(student);

			// commit transaction
			session.getTransaction().commit();

			// MY NEW CODE READING

			// find out what the primary key is: id
			System.out.println("Saved student. Generated id: " + student.getId());

			// now get a new session and start the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrive student based on the id: primary key
			System.out.println("\nGetting student with id: " + student.getId());

			Student myStudent = session.get(Student.class, student.getId());
			System.out.println("Get complete: " + myStudent);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

}
