package or.jsp.SpringBoot.Exception;

public class InvalidCredentialsException extends RuntimeException{
	
		public InvalidCredentialsException(String message) {
			super(message);
		}
}
