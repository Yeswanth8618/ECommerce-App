package or.jsp.SpringBoot.Exception;

public class MerchantNotFoundException extends RuntimeException {
	public  MerchantNotFoundException(String message) {
		super(message);
	}
}
