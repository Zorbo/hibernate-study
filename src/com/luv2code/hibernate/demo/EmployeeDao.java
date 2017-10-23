package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class EmployeeDao {
	
	private Employee theEmployee;
	private SessionFactory factory;
	

	public EmployeeDao(SessionFactory factory) {
		
		this.factory = factory;
	}

	public void createEmployees(String firstName, String lastName, String company) {
		
		Employee employee = new Employee(firstName, lastName, company);
		
		try(Session session = factory.getCurrentSession();) {
		
		System.out.println("Creating a new employee object...");
		
		/*
		Employee employee1 = new Employee("John","Doe","Accenture");
		Employee employee2 = new Employee("Jane","Doe","Accenture");
		Employee employee3 = new Employee("Billy","Bob","ITSH");
		Employee employee4 = new Employee("Zakar","Adam","ITSH");
		Employee employee5 = new Employee("Amanda","Pallmer","Morgan-Stenley");
		*/
		
		session.beginTransaction();
		session.save(employee);
		
		
		session.getTransaction().commit();
		System.out.println("Done!");
		
		}
	}
	
	public List<Employee> queryEmployeeByCompany(String company) {
		
		if(company == null) {
			throw new IllegalArgumentException("Invaild company name: " + company);
		}
		
		try(Session session = factory.getCurrentSession();) {
			
		
			session.beginTransaction();
		
		// create list for query
		List<Employee> employees = session.createQuery("from Employee").getResultList();
		
	    // create query
		employees = session.createQuery("from Employee e where e.company = '" + company + "'").getResultList();
		
		session.getTransaction().commit();
		
		return employees;
		
		}
	}
	
	public Employee getEmployeeById(int employeeId) {
		
		try(Session session = factory.getCurrentSession();) {
			
			session.beginTransaction();
			
			Employee employee = (Employee) session.createQuery("from Employee where id = " + employeeId).getSingleResult();
			
			session.getTransaction().commit();
			
			return employee;
		}	
	}
	
	public void deleteEmployeeById(int employeeId) {
		
		try(Session session = factory.getCurrentSession();) {
			
			session.beginTransaction();
			
			
			Employee myEmployee = session.get(Employee.class, employeeId);			
			session.delete(myEmployee);
			session.getTransaction().commit();
			
			System.out.println("Employee " + myEmployee.getFirstName() + " " + myEmployee.getLastName() + " DELETED!");
			
		}		
	}

}
