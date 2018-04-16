package pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import controller.UserManager;
import dao.AdminDao;
import dao.UserDao;
import exceptions.InvalidDataException;
import exceptions.InvalidUserException;

public class User {
	
	protected int id;
	protected String firstname; 
	protected String lastname; 
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	public boolean activeAccount;
	private Cinema cinema;

	
	public User(String username,String password,String firstname,String lastname,String email, String phoneNumber) throws InvalidUserException {
		setUsername(username);
		setPassword(password);
		setFirstName(firstname);
		setLastName(lastname);
		setEmail(email);
		setPhone(phoneNumber);
	}
	
	public User(int id,String username,String password, String firstname,String lastname, String email, String phoneNumber) throws InvalidUserException {
		this(username, password, firstname, lastname, email, phoneNumber);
		setId(id);
	}
	
	
	
	// getters:
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean getAccountStatus() {
		return this.activeAccount;
	}
	
	public Cinema getCinema() {
		return cinema;
	}

	
	public String getEmail() {
		return email;
	}
	
	// setters:
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) throws InvalidUserException {
		try {
			if(UserManager.verifyUsername(username)){
				this.username = username;
			}
		} catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
	}

	public void setPassword(String password) throws InvalidUserException {
		try {
			if(UserManager.verifyPassword(password)){
				this.password = password;
			}
		} catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
	}
	
	public void setFirstName(String name) throws InvalidUserException {
		try {
			if(UserManager.validation(name)) {
				this.firstname = name;
			}
		}
		catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
	}

	
	public void setLastName(String name) throws InvalidUserException {
		try {
			if(UserManager.validation(name)) {
				this.lastname = name;
			}
		}
		catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
	}
	
	public void setEmail(String email) throws InvalidUserException{
		try {
			if(UserManager.verifyEmail(email)){
				this.email = email;
			}
		}
		catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
		
	}

	public void setPhone(String phone) throws InvalidUserException{
		try {
			if(UserManager.verifyPhoneNumber(phone)){
				this.phone = phone;
			}
		} 
		catch (InvalidDataException e) {
			throw new InvalidUserException(e.getMessage(), e);
		}
	}
	
	
	
// in UserDao
//addMovieToFavoriteList
//rateMovie
//makeReservation
//removeReservation


	
}
