package com.luv2code.hibernate.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;



public class EmployeeMain {

	public static void main(String[] args) {
		
  	   SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		EmployeeDao employeeDao = new EmployeeDao(factory);
		
		//employeeDao.createEmployees("Zakar", "Adam", "ITSH");
		
		for(Employee e : employeeDao.queryEmployeeByCompany("Accenture")) {
			System.out.println(e.getFirstName() + " company: " + e.getCompany());
		}
		
		System.out.println(employeeDao.getEmployeeById(5).getFirstName());
		
		employeeDao.deleteEmployeeById(4);
		
		

	}

}

