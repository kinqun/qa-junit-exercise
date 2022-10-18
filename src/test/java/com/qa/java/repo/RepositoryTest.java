package com.qa.java.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.qa.java.exceptions.EmployeeAlreadyExistsException;
import com.qa.java.exceptions.EmployeeNotFoundException;
import com.qa.java.exceptions.InvalidInputException;
import com.qa.java.model.Employee;

public class RepositoryTest {
	Repository repo;
	
	@BeforeEach
	public void initRepo() {
		repo = new Repository(); 
	}
	
	@AfterEach
	public void resetRepo() {
		repo = null;
	}
	
	@Test
	@DisplayName("getEmpById(id) -> Returns Employee")
	public void givenId_whenGetEmpById_thenReturnEmployee() throws EmployeeNotFoundException, InvalidInputException {
		Employee employee = repo.getEmpById(111);
		assertEquals("emp1", employee.getName());
		assertEquals(111, employee.getId());
		assertEquals(11111.11, employee.getSalary());
	}
	
	@Test
	@DisplayName("getEmpById(id) -> Throws EmployeeNotFoundException")
	public void givenNonExistingId_whenGetEmpById_thenThrowEmployeeNotFoundException() {
		assertThrows(EmployeeNotFoundException.class, ()-> this.repo.getEmpById(11111));
	}
	
	@Test
	@DisplayName("getEmpById(invalid id) -> Throws InvalidInputException")
	public void givenInvalidId_whenGetEmpById_thenThrowInvalidInputExpression() {
		assertThrows(InvalidInputException.class,()->this.repo.getEmpById(-100));
	}
	
	//Write the test cases for getAllEmps
	@Test
	@DisplayName("getAllEmps() -> Returns all Employee List")
	public void givenEmpty_whenGetAllEmps_ReturnEmployeeList() {
		List<Employee> empList = repo.getAllEmps();

		assertEquals(ArrayList.class,empList.getClass());
		assertEquals(3,empList.size());
		for(Employee emp :  empList) {
			assertEquals(Employee.class,emp.getClass());
		}
		
	}
	
	
	//Write the test cases for addEmployee(employee)
	@Test
	@DisplayName("addEmployee(Employee) -> Returns Employee")
	public void givenNewEmployee_whenGetEmployee_thenReturnsNewEmployee() throws EmployeeAlreadyExistsException, InvalidInputException {
		Employee newEmp = new Employee(444,"emp4",44444.44);
		
		Employee addedEmp = this.repo.addEmployee(newEmp);
		assertNotNull(addedEmp);
		assertEquals(Employee.class, addedEmp.getClass());
		assertEquals(newEmp.getId(),addedEmp.getId());
		assertEquals(newEmp.getName(),addedEmp.getName());
		assertEquals(newEmp.getSalary(),addedEmp.getSalary());
	}
	
	@Test
	@DisplayName("addEmployee(conflicting Employee id -> Throws EmployeeAlreadyExistingException" )
	public void givenExistingEmployeeById_whenAddEmployee_thenThrowEmployeeAlreadyExisitngException() {
		Employee dupEmp = new Employee(111, "emp4", 44444.44);
		assertThrows(EmployeeAlreadyExistsException.class, ()-> this.repo.addEmployee(dupEmp));
	}
	
	@Test
	@DisplayName("addEmployee(Employee invalid id) -> Throws InvalidInputException")
	public void givenEmployeeWithInvalidId_whenAddEmployee_thenThrowInvalidInputException() {
		Employee employee = new Employee(-444,"emp4",44444.44);
		assertThrows(InvalidInputException.class, ()-> this.repo.addEmployee(employee));
	}
	
	@Test
	@DisplayName("addEmployee(Employee invalid salary) -> Throws InvalidInputeException")
	public void givenEmployeeWithInvalidSalary_whenAddEmployee_thenThrowInvalidInputException(){
		Employee employee = new Employee(444,"emp4",-44444.44);
		assertThrows(InvalidInputException.class, ()->this.repo.addEmployee(employee));
	}
	
	@Test
	@DisplayName("addEmployee(Employee invalid name) -> Throws InvalidInputException")
	public void givenEmployeeWithInvalidName_whenAddEmployee_thenThrowInvalidInptException() {

		Employee employee = new Employee(444,"$$123emp4",44444.44);
		assertThrows(InvalidInputException.class, ()->this.repo.addEmployee(employee));
	}
	 
}
