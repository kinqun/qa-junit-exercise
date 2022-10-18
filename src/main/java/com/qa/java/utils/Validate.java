package com.qa.java.utils;

public class Validate {
	
	public boolean validIdInput(int id){
		return id > 0 ? true : false;
	}
	
	public boolean validSalaryInput(double salary) {
		return salary > 0 ? true : false;
	}
	
	public boolean validNameInput(String name) {
		//USING NUMBERS AT THE END OF NAMES FOR SIMPLE EXAMPLE PURPOSES ("^[a-zA-Z]+$")
		return name.matches("^[a-zA-Z]+[0-9]+$");
	}
}
