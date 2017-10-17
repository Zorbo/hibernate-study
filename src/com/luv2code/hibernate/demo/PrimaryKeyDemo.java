package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
				
				// create a session
				//Session session = factory.getCurrentSession();
				
				try(Session session = factory.getCurrentSession();) {
					
					// create 3 student object
					System.out.println("Creating 3 student object...");
					Student student1 = new Student("John", "Doe", "john@luv2code.com");
					Student student2 = new Student("Mary", "Public", "marry@luv2code.com");
					Student student3 = new Student("Jane", "Doe", "jane@luv2code.com");
					
					// start a transaction
							session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the students...");
					session.save(student1);
					session.save(student2);
					session.save(student3);
					
					// commit transaction
					session.getTransaction().commit();
					System.out.println("Done!");
				}
			

	}

}
