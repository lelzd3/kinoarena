package pojos;

import exceptions.InvalidUserException;

public class Admin extends User{

	
	private boolean isAdmin = true;
	
	public Admin(String username, String password, String firstname, String lastname, String email, String phoneNumber)
			throws InvalidUserException {
		super(username, password, firstname, lastname, email, phoneNumber);
	}

	
	public Admin(int id, String username, String password, String firstname, String lastname, String email,
			String phoneNumber) throws InvalidUserException {
		super(id, username, password, firstname, lastname, email, phoneNumber);
	}

	
	
	
}
