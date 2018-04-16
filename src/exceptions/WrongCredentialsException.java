package exceptions;

public class WrongCredentialsException extends Exception {

	@Override
	public String getMessage() {
		return "Incorrect username/password/email.";
	}
	
	
}