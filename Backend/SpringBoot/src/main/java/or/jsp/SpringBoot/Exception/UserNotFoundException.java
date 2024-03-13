package or.jsp.SpringBoot.Exception;

public class UserNotFoundException extends RuntimeException{
	public  UserNotFoundException(String message) {
		super(message);
	}
}
