package pojos;

import controller.UserManager;
import exceptions.InvalidDataException;

public class User {
	
	protected int id;
	protected String firstname; 
	protected String lastname; 
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	//public boolean activeAccount; useless maybe
	private Cinema cinema;
	protected boolean isAdmin;
	
	public User(String username,String password,String firstname,String lastname,String email) throws InvalidDataException {
		setUsername(username);
		setPassword(password);
		setFirstName(firstname);
		setLastName(lastname);
		setEmail(email);
		isAdmin=false;
	}
	
	public User(int id,String username,String password, String firstname,String lastname, String email, String phoneNumber) throws InvalidDataException {
		this(username, password, firstname, lastname, email);
		this.phone = phoneNumber;
		setId(id);
	}
	
	
	
	// getters:
	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
//	public boolean getAccountStatus() {
//		return this.activeAccount;
//	}
	
	public Cinema getCinema() {
		return cinema;
	}

	
	public String getEmail() {
		return email;
	}
	
	
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	// setters:
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) throws InvalidDataException {
		if(UserManager.verifyUsername(username)){
			this.username = username;
		}

	}

	public void setPassword(String password) throws InvalidDataException {
		if(UserManager.verifyPassword(password)){
			this.password = password;
		}
	}
	
	public void setFirstName(String name) throws InvalidDataException {
		if(UserManager.validation(name)) {
			this.firstname = name;
		}
	}

	public void setLastName(String name) throws InvalidDataException {
		if(UserManager.validation(name)) {
			this.lastname = name;
		}
	}
	
	public void setEmail(String email) throws InvalidDataException{
		if(UserManager.verifyEmail(email)){
			this.email = email;
		}
	}

	public void setPhone(String phone) throws InvalidDataException{
		if(UserManager.verifyPhoneNumber(phone)){
			this.phone = phone;
		}
	
	}



	
}
