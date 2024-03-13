package or.jsp.SpringBoot.Exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Id";
	}
}
