package pojos;

import exceptions.InvalidDataException;

public class Admin extends User{

	
	
	public Admin(String username, String password, String firstname, String lastname, String email)
			throws InvalidDataException {
		super(username, password, firstname, lastname, email);
		isAdmin = true;
	}

	
	public Admin(int id, String username, String password, String firstname, String lastname, String email,
			String phoneNumber) throws InvalidDataException {
		super(id, username, password, firstname, lastname, email, phoneNumber);
		isAdmin = true;
	}

	
	
	
}
