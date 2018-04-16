package exceptions;

public class InvalidUserException extends Exception {

	public InvalidUserException(String message, Exception cause)  {
		super(message, cause);
	}
	
	
}
