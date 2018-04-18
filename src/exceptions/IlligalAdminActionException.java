package exceptions;

public class IlligalAdminActionException extends Exception {
	
	public IlligalAdminActionException(){
		super("Admin can not do this action!");
	}
	
	public IlligalAdminActionException(Throwable cause){
		super("Admin can not do this action!", cause);
	}

}
