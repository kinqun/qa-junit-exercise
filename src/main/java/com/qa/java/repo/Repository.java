package com.qa.java.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.InputMap;

import com.qa.java.exceptions.EmployeeAlreadyExistsException;
import com.qa.java.exceptions.EmployeeNotFoundException;
import com.qa.java.exceptions.InvalidInputException;
import com.qa.java.model.Employee;
import com.qa.java.utils.Validate;

public class Repository {
	List<Employee> empList;
	Validate validate;

	public Repository() {
		empList = new ArrayList<>();
		this.empList.add(new Employee(111,"emp1", 11111.11)); 
		this.empList.add(new Employee(222,"emp2", 22222.22));
		this.empList.add(new Employee(333,"emp3", 33333.33));
		
//		this.empList = Arrays.asList(
//				new Employee(111,"emp1", 11111.11), 
//				new Employee(222,"emp2", 22222.22),
//				new Employee(333,"emp3", 33333.33));
		validate = new Validate();
	};
	
	public Employee getEmpById(int id) throws EmployeeNotFoundException, InvalidInputException {
		Employee employee = null;
		if(!validate.validIdInput(id)) {
			throw new InvalidInputException("must have postive integer");
		}
		employee = empList.stream().filter(e->e.getId() == id).findFirst().orElseThrow(()-> new EmployeeNotFoundException("cant find employee by given id"));
		
		return employee;
	}
	
	public List<Employee> getAllEmps(){
		return this.empList;
	}
	 
	public Employee addEmployee(Employee emp) throws EmployeeAlreadyExistsException, InvalidInputException{
		Employee addedEmp = null;
		int baseListSize = this.empList.size();
		
		if(!validate.validIdInput(emp.getId())) {
			throw new InvalidInputException("id requires a positive integer");
		}
		if(!validate.validSalaryInput(emp.getSalary())) {
			throw new InvalidInputException("salary requires a positive double");
		}
		if(!validate.validNameInput(emp.getName())) {
			throw new InvalidInputException("invalid name input");
		}
		for(Employee e : this.empList) {
			if(e.getId() == emp.getId()) {
				throw new EmployeeAlreadyExistsException("cant add, employee already in list");
			}
		}
		
		this.empList.add(emp);
		
		if(this.empList.size() == baseListSize + 1) {
			addedEmp = emp;
		}
		return addedEmp;
	}
}
