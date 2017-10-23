package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create a session
		//Session session = factory.getCurrentSession();
		
		try(Session session = factory.getCurrentSession();) {
		
			
			// start a transaction
					session.beginTransaction();
					
		    // query the students
					List<Student> students = session.createQuery("from Student").getResultList();
					
			// display the students
					printStudents(students);
					
			// query students: lastName = 'Doe'
					students = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
					System.out.println("Students who have last name of Doe");
				    printStudents(students);
				    
		    // query students: lastName='Doe' OR firstName = 'Daffy'
				    students = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName = 'Bill'").getResultList();
				    System.out.println("Students who have last name of Doe or first name is Bill");
				    printStudents(students);
				    
		    // query students where email LIKE '%luv2code.com'
				    students = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
				    		 System.out.println("Students where email ends with luv2code.com");
				    		 printStudents(students);
				    
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
	
		
		//

	}

	private static void printStudents(List<Student> students) {
		for (Student s : students) {
			System.out.println(s);
		}
	}

}
