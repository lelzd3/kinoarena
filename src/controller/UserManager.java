package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.DBManager;
import exceptions.InvalidDataException;

public class UserManager {
	
	//Singleton and DBconnection
	private static Connection connection;
	private static UserManager instance;

	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	private UserManager() {
		connection = DBManager.getInstance().getConnection();
	}
		
	

	public static boolean inputValidation(String username, String password) throws InvalidDataException {
		if(validation(username) && validation(password)) {
			return true;
		}
		else {
			throw new InvalidDataException("Invalid User name or password");
		}
	}

	public static boolean validation(String text)  throws InvalidDataException {
		if( text != null && !text.trim().isEmpty()){
			return true;
		}
		else {
			throw new InvalidDataException("Invalid User name or password");
		}
	}
	
	public static boolean verifyEmail(String email) throws InvalidDataException {
	    if(!validation(email)) {
	    	 throw new InvalidDataException("Invalid e-mail");
	    }
	      
	    if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	    	 throw new InvalidDataException("Invalid e-mail");
	    }   
	    return true;
	}
	
	public static boolean verifyName(String name) throws InvalidDataException {
		if(!validation(name)) {
			throw new InvalidDataException("Invalid Name");
	    }

	    if(!name.matches("[a-zA-Z]*")) {
			throw new InvalidDataException("Invalid Name");
	    }
	        
	    return true;
	}
	
	public static boolean verifyPhoneNumber(String number) throws InvalidDataException {
		if(!validation(number)) {
			throw new InvalidDataException("Invalid Phone number");
	    }

	    if(number.length() != 10 || !number.matches("[0-9]+")) {
			throw new InvalidDataException("Invalid Phone number");
	    }
	        
	    return true;
	}
	
	public static boolean verifyUsername(String username) throws InvalidDataException {

	    if(!username.matches("[A-Za-z0-9_]+") || username.length() < 6) {
			throw new InvalidDataException("Invalid username");
	    }
	        
	    return true;
	}
	
	public static boolean verifyPassword(String password) throws InvalidDataException {
		if(!validation(password)) {
			throw new InvalidDataException("Invalid password");
	    }

	    if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)") || password.length() < 6) {
	    	System.out.println("Please enter valid password: atleast one digit, one lowercase, one uppercase and at least one symbol!");
	    	throw new InvalidDataException("Invalid password");
	    }
	        
	    return true;
	}


}
